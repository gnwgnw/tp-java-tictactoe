define([
		'backbone',
		'models/player'
	],
	function(
		Backbone,
		player
	)
	{
		var Collection = Backbone.Collection.extend({

			model: player,
			url: '/scores',

			comparator: function(a, b) {
				return b.get('gameCount') - a.get('gameCount');
			}
		});

		return new Collection();
	});