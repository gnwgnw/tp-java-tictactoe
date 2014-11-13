define([
	'backbone'
	],
	function(
		Backbone
	)
{
    var Model = Backbone.Model.extend({
	initialize: function() {	    	
	},
        defaults: {
            status: 'none',
            position: -1,
            img_url: '/img/none.jpg'
        }
    });

    return Model;
});