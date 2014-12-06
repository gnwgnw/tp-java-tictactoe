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
                    login: "",
                    email: "",
                    gameCount: 0,
                    gameLose: 0,
                    gameWin: 0,
                    isLogined: false
                });
            },

            isLogined: function () {
                return this.get('isLogined');
            },

            signup: function (data) {//TODO
                var that = this;

                $.post(
                    "/signup",
                    data,
                    function(data) {
                        console.log(data);
                        if (data.status == "OK") {
                            that.trigger('signup:ok');
                        }
                    },
                    'json'
                );
            },

            login: function (data) {//TODO
                var that = this;

                $.post(
                    "/login",
                    data,
                    function(data) {
                        if (data.status == "OK") {
                            that.setUser(data);
                            that.trigger('login:ok');
                        }
                    },
                    'json'
                );
            },

            logout: function () {
                $.post(
                    "/logout",
                    function(data) {
                        console.log(data);
                    },
                    'json'
                );

                this.defaults();
            },

            setUser: function (data) {
                this.set(data.response.user);
                this.set('isLogined', true);
            }
        });

        return new Model();
    });