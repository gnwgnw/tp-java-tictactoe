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
				return b.get('gameWin') - a.get('gameWin');
			}
		});

		return new Collection();
	});