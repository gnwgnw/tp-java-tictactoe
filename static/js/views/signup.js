define([
        'backbone',
        'models/user',
        'tmpl/signup'
    ],
    function(
        Backbone,
        user,
        tmpl
    )
    {
        var View = Backbone.View.extend({

            id: "signup",
            template: tmpl,
            model: user,

            events: {
                "submit": "signup"
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

            signup: function (event) {
                event.preventDefault();

                var url = "/signup";
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