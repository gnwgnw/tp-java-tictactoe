define([
        'backbone',
        'tmpl/game',
        'views/battlefield',
        'models/user'
    ],
    function(
        Backbone,
        tmpl,
        battlefield,
        user
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

                this.initField();
                this.initWS();
            },

            doTurn: function (position) {
                //TODO
                console.log(position);
            },

            initField: function () {
                this.field = new battlefield();
                this.listenTo(this.field, 'turn', this.doTurn);

                this.$el.find('#battlefield').html(this.field.$el);

                //TODO test
                this.field.setField([1, 4, 1, 0, 0, 0, 4, 1, 4]);
            },

            initWS: function () {
                this.ws = new WebSocket("ws://127.0.0.1:8080/gameplay");

                this.ws.onopen = function () {
                    //TODO
                };

                this.ws.onmessage = function () {
                    //TODO
                };

                this.ws.onclose = function () {
                    //TODO
                };
            }
        });

        return new View();
    });