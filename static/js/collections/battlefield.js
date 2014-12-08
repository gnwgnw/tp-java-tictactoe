define([
		'backbone',
		'models/battlefieldNode'
	],
	function(
		Backbone,
		node
	)
	{
		var Collection = Backbone.Collection.extend({

			model: node
		});

		return Collection;
	});