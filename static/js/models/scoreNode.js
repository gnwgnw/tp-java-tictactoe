/**
 * Created by gexogen on 03.12.14.
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
                    name: "",
                    gameCount: 0,
                    gameLose: 0,
                    gameWin: 0
                });
            }
        });

        return Model;
    });