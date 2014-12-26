define([
        'backbone',
        'tmpl/game',
        'views/battlefield',
        'views/player-status',
        'views/battle-status',
        'models/user',
        'models/player'
    ],
    function(
        Backbone,
        tmpl,
        battlefield,
        playerStatus,
        battleStatus,
        user,
        player
    )
    {
        var View = Backbone.View.extend({

            id: "game",
            template: tmpl,
            model: user,

            events:{
                "click #new-game-js": "show"
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
                    this.initStatus();
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
                    that.battleStatus.setStatus(_.pick(data, "whoseTurn", "isFinished", "winner"));

                    that.myStatus.show();
                    that.enemyStatus.show();
                    that.battleStatus.show();
                };

                this.ws.onclose = function () {
                    //TODO clear players, battelfield
                    that.ws = undefined;
                };
            },

            initStatus: function () {
                this.myPlayer = new player({who: "user"});
                this.enemyPlayer = new player({who: "enemy"});

                this.myStatus = new playerStatus({model: this.myPlayer, el: '#my-info'});
                this.enemyStatus = new playerStatus({model: this.enemyPlayer, el: '#enemy-info'});

                this.battleStatus = new battleStatus({el: '#status'});
            }
        });

        return new View();
    });