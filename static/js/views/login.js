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
                "submit #login-form": "login"
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
                var data = this.$el.find('form').serializeObject();
                this.model.login(data);
            }
        });

        return new View();
    });