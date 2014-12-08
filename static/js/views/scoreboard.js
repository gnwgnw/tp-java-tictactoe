define([
        'backbone',
        'tmpl/scoreboard',
        'collections/scores'
    ],
    function(
        Backbone,
        tmpl,
        scores
    )
    {
        var View = Backbone.View.extend({

            id: "scoreboard",
            template: tmpl,
            collection: scores,

            events: {
            },

            initialize: function() {
                this.listenTo(this.collection, 'reset', this.render);
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.collection.toJSON()));
                return this;
            },

            show: function () {
                this.trigger('show');
                this.collection.fetch({reset: true});
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            }
        });

        return new View();
    });