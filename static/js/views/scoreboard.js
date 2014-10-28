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
        events: {
            "show" : "show",
        },
        show: function () {
            this.$el.css({'display':'block'});
            if (!$('#scoreboard').html()) {
                $('#scoreboard').html(this.$el);
            }            
            this.trigger("show");
        },
        hide: function () {            
            this.$el.css({'display':'none'})
        }

    });

    return new View({collection: Collection});
});