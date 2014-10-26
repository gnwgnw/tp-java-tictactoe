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
                console.log(col.get('status'))
                if (col.get('status')=='x') {
                    col.set({'img_url': '/img/x.jpg'});
                }
                else if (col.get('status')=='o') {
                    col.set({'img_url': '/img/o.jpg'});
                }
            });
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
    console.log('before return')
    return new View({collection: Collection});
});