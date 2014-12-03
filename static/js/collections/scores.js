define([
		'backbone',
		'models/scoreNode'
	],
	function(
		Backbone,
		scoreNode
	)
	{
		var Collection = Backbone.Collection.extend({

			model: scoreNode,
			url: '/scores',

			comparator: function(a, b) {
				return b.get('gameCount') - a.get('gameCount');
			}
		});

		return new Collection();
	});