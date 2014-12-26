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
                "submit #signup-form": "signup"
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
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            signup: function (event) {
                console.log(1);
                event.preventDefault();
                var data = this.$el.find('form').serializeObject();
                this.model.signup(data);
            }
        });

        return new View();
    });