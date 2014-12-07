/**
 * Created by gexogen on 07.12.14.
 */
define([
        'backbone'
    ],
    function(
        Backbone
    )
    {
        var Model = Backbone.Model.extend({

            defaults: function () {
                this.set({
                    isFinished: false,
                    whoseTurn: "none",
                    winner: "none"
                });
            }
        });

        return Model;
    });