define([
        'backbone'
    ],
    function(
        Backbone
    )
    {
        var Model = Backbone.Model.extend({

            defaults: function () {
                //TODO
                this.set({
                    name: "",
                    gameCount: 0,
                    gameLose: 0,
                    gameWin: 0,
                    isLogined: false
                });
            },

            isLogined: function () {
                return this.get('isLogined');
            },

            signup: function (data) {
                //TODO ajax
            },

            login: function (data) {
                //TODO
                this.testLogin();
            },

            logout: function () {
                //TODO
                this.defaults();
            },

            //TODO testOnly
            testLogin: function () {
                this.set({
                    name: "Gex",
                    gameCount: 5,
                    gameLose: 1,
                    gameWin: 3,
                    isLogined: true
                });
                console.log(this.toJSON());
            }
        });

        return new Model();
    });