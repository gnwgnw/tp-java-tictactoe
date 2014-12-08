define([
        'backbone'
    ],
    function(
        Backbone
    )
    {
        var Model = Backbone.Model.extend({

            defaults: {
                status: 'none'
            }
        });

        return Model;
    });