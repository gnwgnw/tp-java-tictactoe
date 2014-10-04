define([
    'backbone',
    'tmpl/scoreboard',
    'collections/scores'
    ],
    function(
        Backbone,
        tmpl,
        Collection
    )
{
    var View = Backbone.View.extend({

        template: tmpl,
        initialize: function() {
            this.listenTo(this.collection, 'reset', this.render);
        },
        render: function () {
            this.$el.html( this.template( this.collection.toJSON() ) );
            return this;
        },
        show: function () {
            // TODO
        },
        hide: function () {
            // TODO
        }

    });

    return new View({collection: Collection});
});