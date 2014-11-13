define([
    'backbone',
    'tmpl/game',
    'collections/cells'
    ],
    function(
        Backbone,
        tmpl,
        Collection
    )
{
    var View = Backbone.View.extend({

        template: tmpl,
        initialize: function () {            
            this.render();
        },
        render: function () {            
            Collection.each(function(col){                
                if (col.get('status')=='x') {
                    col.set({'img_url': '/img/x.jpg'});
                }
                else if (col.get('status')=='o') {
                    col.set({'img_url': '/img/o.jpg'});
                }
                else {
                    col.set({'img_url': '/img/none.jpg'});
                }
            });
            this.$el.html( this.template( this.collection.toJSON() ) );
            return this;
        },
        events:{
            "show": "show"
        },
        show: function () {
            this.$el.css({'display':'block'});                
            if (!$('#game').html()) {            
                $('#game').html(this.$el);
            }                        
            this.trigger("show", this);
        },
        hide: function () {
            this.$el.css({'display':'none'})
        }

    });    
    return new View({collection: Collection});
});