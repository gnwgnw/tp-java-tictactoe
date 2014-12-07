define([
        'backbone',
        'tmpl/game',
        'views/battlefield',
        'views/player-status',
        'models/user',
        'models/player'
    ],
    function(
        Backbone,
        tmpl,
        battlefield,
        playerStatus,
        user,
        player
    )
    {
        var View = Backbone.View.extend({

            id: "game",
            template: tmpl,
            model: user,

            events:{
            },

            initialize: function () {
                this.render();
            },

            render: function () {
                this.$el.html(this.template());
                return this;
            },

            show: function () {
                this.trigger('show');

                //TODO user not logined
                this.startGame();
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            startGame: function () {
                //TODO validate ws
                if (this.ws == undefined) {
                    this.initField();
                    this.initBattleStatus();
                    this.initWS();
                }
            },

            doTurn: function (position) {
                //TODO
                //TODO validate ws
                console.log(position);

                if (this.ws != undefined) {
                    this.ws.send(position);
                }
            },

            initField: function () {
                this.field = new battlefield();
                this.listenTo(this.field, 'turn', this.doTurn);

                this.$el.find('#battlefield').html(this.field.$el);
            },

            initWS: function () {
                var that = this;
                this.ws = new WebSocket("ws://127.0.0.1:8080/gameplay");

                this.ws.onopen = function (event) {
                    //TODO show status of connection
                    console.log(event);
                };

                this.ws.onmessage = function (event) {
                    var data = JSON.parse(event.data);

                    //TODO
                    console.log(data);

                    that.field.setField(data.field);

                    that.myPlayer.setPlayer(data.myGameUser);
                    that.enemyPlayer.setPlayer(data.enemyGameUser);

                    that.myStatus.show();
                    that.enemyStatus.show();
                };

                this.ws.onclose = function () {
                    //TODO clear players, battelfield
                    that.ws = undefined;
                };
            },

            initBattleStatus: function () {
                this.myPlayer = new player();
                this.enemyPlayer = new player();

                this.myStatus = new playerStatus({model: this.myPlayer, el: '#my-info'});
                this.enemyStatus = new playerStatus({model: this.enemyPlayer, el: '#enemy-info'});
            }
        });

        return new View();
    });