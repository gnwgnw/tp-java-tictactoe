define([
		'backbone',
		'models/cell'
	],
	function(
		Backbone,
		Model
	)
	{
		var Collection = Backbone.Collection.extend({

			model: Model,
			url: '/game',

			initialize: function() {
			},

			comparator: function(a, b) {
				return a.get('position') - b.get('position');
			}
		});

		return new Collection();
	});