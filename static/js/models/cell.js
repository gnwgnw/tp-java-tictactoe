define([
	'backbone'
	],
	function(
		Backbone
	)
{
    var Model = Backbone.Model.extend({
	initialize: function() {
	    console.log('model init')	   
	},
        defaults: {
            status: 'none',
	    position: -1,
	    img_url: '/img/none.jpg'
        }
    });

    return Model;
});