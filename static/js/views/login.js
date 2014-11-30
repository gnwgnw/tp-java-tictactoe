define([
        'backbone',
        'models/user',
        'tmpl/login'
    ],
    function(
        Backbone,
        user,
        tmpl
    )
    {
        var View = Backbone.View.extend({

            id: "login",
            template: tmpl,
            model: user,

            events: {
                "submit": "login"
            },

            initialize: function () {
                this.render();
            },

            render: function () {
                this.$el.html(this.template());
                return this;
            },

            show: function () {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            login: function (event) {
                event.preventDefault();

                var url = "/login";
                var data = this.$el.find('form').serializeObject();

                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    data: data,
                    success: function (data) {
                        //TODO
                        console.log(data);
                    },
                    failure: function (data) {
                        //TODO
                        console.log(data);
                    }
                });
            }
        });

        return new View();
    });