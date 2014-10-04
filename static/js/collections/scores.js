define([
	'backbone',
	'models/score'
	],
	function(
		Backbone,
		Model
	)
{
    var Collection = Backbone.Collection.extend({

		model: Model,
		url: '/scores',
		initialize: function() {
			this.fetch({reset: true});
		},
		comparator: function(a, b) {
			return b.get('score') - a.get('score');
		}
    });

    return new Collection();
});