/**
 * Created by gexogen on 07.12.14.
 */
define([
        'backbone',
        'tmpl/battle-status',
        'models/status'
    ],
    function(
        Backbone,
        tmpl,
        status
    )
    {
        var View = Backbone.View.extend({

            template: tmpl,

            events:{
            },

            initialize: function () {
                this.model = new status();
                this.listenTo(this.model, "change", this.render);
                this.hide();
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model.toJSON()));
                return this;
            },

            show: function () {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            setStatus: function (data) { //TODO очень говнокод, я прямо глаза хочу сам себе выкулопать и руки сломать
                var temp;
                switch (data.whoseTurn) {
                    case 1:
                        temp = 'x';
                        break;
                    case 4:
                        temp = 'o';
                        break;
                    default :
                        temp = "none";
                }
                data.whoseTurn = temp;

                switch (data.winner) {
                    case 1:
                        temp = 'x';
                        break;
                    case 4:
                        temp = 'o';
                        break;
                    default :
                        temp = "none";
                }
                data.winner = temp;

                this.model.set(data);
            }
        });

        return View;
    });