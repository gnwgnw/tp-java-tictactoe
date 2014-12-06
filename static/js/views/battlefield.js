/**
 * Created by gexogen on 04.12.14.
 */
define([
        'backbone',
        'tmpl/battlefield',
        'collections/battlefield'
    ],
    function(
        Backbone,
        tmpl,
        battlefield
    )
    {
        var View = Backbone.View.extend({

            template: tmpl,

            events:{
                "click .battlefield-node": "doTurn"
            },

            initialize: function () {
                this.collection = new battlefield();
                this.listenTo(this.collection, "reset", this.render);
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.collection.toJSON()));
                return this;
            },

            show: function () {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            setField: function (field) {
                var collection = [];
                for (var i = 0; i < field.length; ++i) {
                    var status;
                    switch (field[i]) {
                        case 1:
                            status = 'x';
                            break;
                        case 4:
                            status = 'o';
                            break;
                        default:
                            status = 'none';
                    }
                    collection.push({id: i, status: status});
                }
                this.collection.reset(collection);
            },

            doTurn: function (event) {
                var position = event.currentTarget.id;
                this.trigger('turn', position);
            }
        });

        return View;
    });