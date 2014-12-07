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
                    login: "",
                    sign: ""
                });
            }
        });

        return Model;
    });