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
                    sign: "none"
                });
            },

            setPlayer: function (data) {
                switch (data.sign) {
                    case 1:
                        data.sign = 'x';
                        break;
                    case 4:
                        data.sign = 'o';
                        break;
                    default :
                        data.sign = "none";
                }

                this.set(data);
            }
        });

        return Model;
    });