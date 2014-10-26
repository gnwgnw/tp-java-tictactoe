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
			console.log('init collection');
		},
		comparator: function(a, b) {
			return a.get('position') - b.get('position');
		}
    });

    return new Collection([{status: 'x', position: 0 },
			   {status: 'o', position: 1},
			   {status: 'o', position: 2},
			   {status: 'x', position: 3},
			   {status: 'x', position: 4},
			   {status: 'o', position: 5},
			   {status: 'o', position: 6},
			   {status: 'x', position: 7},
			   {status: 'x', position: 8},
			   ]);
});