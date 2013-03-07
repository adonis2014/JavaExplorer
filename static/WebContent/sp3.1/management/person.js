/**
 * initialize app,global values.
 */
app = window.app || {};

constant = window.constant || {};

constant.servletPath = location.pathname.substring(0, location.pathname.indexOf("/management/person"));

constant.currentDate = new Date();
constant.localOffsetMinute = constant.currentDate.getTimezoneOffset();
constant.localOffsetMillisecond = constant.localOffsetMinute * 60000;
constant.offsetMillisecond = 3600000;

/**
 * initialize model
 */
PersonModel = Backbone.Model.extend({
	defaults : {
		"name" : null,
		"sex" : null,
		"birthday" : null,
		"breakfastTime" : null,
		"createTime" : null,
		"height" : null,
		"languages" : null,
		"note" : null,
		"salary" : null,
		"active" : null,
		"version" : null
	},

	initialize : function(attributes, options) {
		// deep clone attributes of this model.
		this.original = $.extend(true, {}, this.attributes);
	},

	parse : function(response, options) {
		if (response.processSuccessfully == null) {
			var dateSplit = response.birthday.match(/\d+/g);
			response.birthday = new Date(Date.UTC(dateSplit[0], parseInt(dateSplit[1]) - 1, dateSplit[2]));

			dateSplit = response.breakfastTime.match(/\d+/g);
			response.breakfastTime = new Date(Date.UTC(0, 0, 0, dateSplit[0], dateSplit[1], dateSplit[2]));

			response.createTime = new Date(response.createTime + constant.localOffsetMillisecond);

			if (!(response.languages instanceof Array)) {
				response.languages = [];
			}
			response.languages.sort();
			return response;
		} else {
			return undefined;
		}
	},

	toJSON : function(options) {
		return Backbone.Model.prototype.toJSON.apply(this, arguments);
	},

	validate : function(attributes, options) {
		if (attributes.id == null) {
			return "id is required!";
		}
		if (attributes.name == null) {
			return "name is required!";
		}
		if (attributes.sex != "male" && attributes.sex != "female") {
			return "argument of sex is wrong!";
		}
		if (!(attributes.birthday instanceof Date)) {
			return "argument of birthday is wrong!";
		}
		if (!(attributes.breakfastTime instanceof Date)) {
			return "argument of breakfastTime is wrong!";
		}
		if (typeof attributes.height !== "number" || isNaN(attributes.salary)) {
			return "argument of salary is wrong!";
		}
		if (typeof attributes.salary !== "number" || isNaN(attributes.salary) || /[\.-]/.test(attributes.salary + "")) {
			return "argument of salary is wrong!";
		}
		if (typeof attributes.active !== "boolean") {
			return "argument of active is wrong!";
		}
		return undefined;
	}
});

/**
 * initialize collection
 */
PersonCollection = Backbone.Collection.extend({
	model : PersonModel,
	url : constant.servletPath + "/person",

	initialize : function(models, options) {
	},

	parse : function(response) {
		return response.persons;
	}
});

/**
 * initialize collection
 */
BatchUpdatePersonCollection = Backbone.Collection.extend({
	model : PersonModel,
	url : constant.servletPath + "/person",

	initialize : function(models, options) {
	},

	batchUpdate : function(options) {
		options = options || {};
		var success = options.success;
		var error = options.error;

		options.success = function(collection, resp, options) {
			var removedModel = [];
			$.each(resp.actionStatus, function(index, actionStatu) {
				if (actionStatu.processSuccessfully) {
					var model = collection.get(actionStatu.id);
					model.original = $.extend(true, {}, model.attributes);
					model.trigger("change", model, options);
					if (model) {
						removedModel.push(model);
						collection.remove(model, {
							silent : true
						});
					}
				}
			});
			if (removedModel.length > 0) {
				collection.trigger("remove", removedModel, collection, options);
			}

			if (success) {
				success(collection, resp, options);
			}
		};

		options.error = function(collection, xhr, options) {
			if (error) {
				error(collection, xhr, options);
			}
		};

		var xhr = this.sync("update", this, options);
		return xhr;
	},
	toJSON : function(options) {
		var attributes = Backbone.Collection.prototype.toJSON.apply(this, arguments);
		var jsonDate = {};
		jsonDate.personCommands = attributes;
		return jsonDate;
	},
	parse : function(response) {
		return response.persons;
	}
});

// initialize view

/**
 * pereson tr view. this view collection is PersonCollection.
 */
PersonTbodyView = Backbone.View.extend({
	initialize : function(options) {
		this.adaptScroll = options.adaptScroll;

		this.collection.on("reset", this.collectionResetListener, this);

		this.$contentPanel = $("table>tbody", this.$el);

		// fetch data from service
		this.collection.fetch({
			success : function(collection, response, options) {
				alert("fetch data success!");
			},
			error : function(collection, xhr, options) {
				alert("person fetch error!");
			}
		});
	},

	render : function() {
		this.$contentPanel.empty();
		this.collection.each($.proxy(function(model, index, collection) {
			var view = new PersonTrView({
				model : model
			});
			this.$contentPanel.append(view.render().$el);
		}), this);
		this.fixScrollBar();
		return this;
	},
	fixScrollBar : function() {
		var overflowY = this.adaptScroll($("div.headtable", this.$el), $("table:not(.head)", this.$el));
		$("div.body", this.$el).css("overflow-y", overflowY);
	},
	collectionResetListener : function(collection, options) {
		this.render();
	}
});

/**
 * pereson tr view. this view model is PersonModel.
 */
PersonTrView = Backbone.View.extend({

	template : _.template("<td class='ui-widget-content'><span class='id-style'><%=id%></span></td>" + "<td class='ui-widget-content'><%=name%></td>"
			+ "<td class='ui-widget-content'><%=sex%></td>" + "<td class='ui-widget-content'><%=birthday.formatUTC('yyyy年MM月dd日')%></td>"
			+ "<td class='ui-widget-content'><%=breakfastTime.formatUTC('hh时mm分ss秒')%></td>"
			+ "<td class='ui-widget-content'><%=createTime.formatUTC('yyyy-MM-dd hh:mm:ss')%></td>" + "<td class='ui-widget-content'><%=height%></td>"
			+ "<td class='ui-widget-content'><ul><%_.each(languages,function(name){print('<li>'+name+'</li>')})%></ul></td>"
			+ "<td class='ui-widget-content'><%=salary%></td>" + "<td class='ui-widget-content'><%=note%></td>"
			+ "<td class='ui-widget-content'><%=version%></td>" + "<td class='ui-widget-content'><%=active?'是':'否'%></td>"
			+ "<td class='ui-widget-content'><button type='button' class='modifyBtn'>修改</button>"
			+ "<button type='button' class='saveBtn' disabled='disabled'>保存</button>"
			+ "<button type='button' class='resetBtn' disabled='disabled'>还原</button>" + "<button type='button' class='deleteBtn'>删除</button></td>"),

	tagName : "tr",

	events : {
		"click button.modifyBtn" : "modifyPersonInfoAction",
		"click button.saveBtn" : "savePersonInfoAction",
		"click button.resetBtn" : "resetPersonInfoAction",
		"click button.deleteBtn" : "deletePersonInfoAction"
	},

	initialize : function(options) {
		this.model.on("change", this.changeAttributeListener, this);

		this.model.on("invalid", this.modelValidateFailedListener, this);

		this.model.on("destroy", this.modelDestroyListener, this);
	},
	changeAttributeListener : function(model) {
		this.render();

		if (!_.isEqual(this.model.original, this.model.attributes)) {
			$(".id-style", this.$el).css({
				color : "red",
				"font-weight" : "bold"
			});
			$(":button:gt(0)", this.$el).prop("disabled", false);
			app.batchUpdatePersonCollection.push(this.model);
		} else {
			app.batchUpdatePersonCollection.remove(this.model);
		}
	},
	render : function() {
		this.$el.html(this.template(this.model.attributes));
		return this;
	},

	modifyPersonInfoAction : function(event) {
		app.modifyPersonview.setModel(this.model);
		$("#dialog-edit-person").dialog("open");
	},
	savePersonInfoAction : function(event) {
		this.model.save(null, {
			success : function(model, response, options) {
				model.original = $.extend(true, {}, model.attributes);
				model.trigger("change", model);
				alert("save complete!");
			},
			error : function(model, xhr, options) {
				alert("save error!");
			}
		});
	},
	resetPersonInfoAction : function(event) {
		var originalValue = $.extend(true, {}, this.model.original);
		delete originalValue.id;
		this.model.set(originalValue);
	},
	deletePersonInfoAction : function(event) {
		if (confirm("是否要删除所选定的记录？")) {
			this.model.destroy({
				success : $.proxy(function(model, response, options) {
					if (response.processSuccessfully) {
						this.remove();
						alert("delete success!");
					} else {
						alert("delete error!");
					}
				}, this),
				error : function(model, xhr, options) {
					alert("delete error!");
				}
			});
		}
	},
	modelValidateFailedListener : function(model, error, options) {
		alert("Model validate failed! " + error);
	},
	modelDestroyListener : function(model, collection, options) {
	}
});

/**
 * edit person info this model is temporary PersonModel
 */
EditPersonFromView = Backbone.View
		.extend({
			template : _
					.template("<form action=''>"
							+ "id:<input name='id' type='text' value='<%=id%>' disabled='disabled'/><br/>"
							+ "姓名:<input name='name' type='text' value='<%=name%>'/><br/>"
							+ "性别:男<input name='sex' type='radio' value='male' <%if(sex=='male'){print('checked=\"checked\"')}%>/>女<input name='sex' type='radio' value='female' <%if(sex=='female'){print('checked=\"checked\"')}%>/><br/>"
							+ "生日:<input name='birthday' type='text' class='datepicker' readonly='readonly' value='<%=birthday.formatUTC('yyyy-MM-dd')%>'/><br/>"
							+ "早餐时间:<input name='breakfastTime' type='text' value='<%=breakfastTime.formatUTC('hh:mm:ss')%>'/><br/>"
							+ "身高:<input name='height' type='text' value='<%=height%>'/><br/>"
							+ "语言:"
							+ "英文<input name='languages' type='checkbox' value='english' <%if(_.find(languages,function(element){return element=='english'})){print('checked=\"checked\"')}%>/>"
							+ "中文<input name='languages' type='checkbox'S value='chinese' <%if(_.find(languages,function(element){return element=='chinese'})){print('checked=\"checked\"')}%>/>"
							+ "日文<input name='languages' type='checkbox' value='japanese' <%if(_.find(languages,function(element){return element=='japanese'})){print('checked=\"checked\"')}%>/>"
							+ "韩文<input name='languages' type='checkbox' value='korean' <%if(_.find(languages,function(element){return element=='korean'})){print('checked=\"checked\"')}%>/>"
							+ "其它<input name='languages' type='checkbox' value='other' <%if(_.find(languages,function(element){return element=='other'})){print('checked=\"checked\"')}%>/><br/>"
							+ "收入:<input name='salary' type='text' value='<%=salary%>'/><br/>"
							+ "备注:<textarea rows='5' cols='20' name='note'><%=note%></textarea><br/>"
							+ "激活:是<input name='active' type='radio' value='true' <%=active?'checked=\"checked\"':''%>/>否<input name='active' type='radio' value='false' <%=active?'':'checked=\"checked\"'%>/>"
							+ "</form>"),

			events : {
				"click button.ui-button-text-only:first" : "submitAction",
				"click button.ui-button-text-only:eq(1)" : "cancelAction",
				"click button.ui-button-text-only:last" : "showDataAction",

				"change input[name='name'],textarea[name='note']" : "changeTextAction",
				"change input[name='birthday']" : "changeDateAction",
				"change input[name='breakfastTime']" : "changeTimeAction",
				"keyup input[name='breakfastTime']" : "validateTimeAction",
				"change input[name='height']" : "changeFloatAction",
				"change input[name='salary']" : "changeIntAction",
				"change input:radio[name='active']" : "changeRadioAction",
				"change input[name='languages']:checkbox" : "changeCheckboxAction"
			},

			initialize : function(options) {
				this.$contentPanel = $("#dialog-edit-person", this.$el);

				this.model.on("invalid", this.modelValidateFailedListener, this);

				this.model.on("change", this.changeAttributeListener, this);
			},

			setModel : function(newModel) {
				this.model.set($.extend(true, {}, newModel.attributes));
			},

			changeAttributeListener : function(model) {
				this.render();
			},

			render : function() {
				this.$contentPanel.html(this.template(this.model.attributes));

				$("input.datepicker", this.$contentPanel).datepicker({
					dateFormat : "yy-mm-dd"
				});

				return this;
			},

			submitAction : function(event) {
				var targetModel = app.personCollection.get(this.model.id);
				var newAttributes = _.clone(this.model.attributes);
				delete newAttributes.id;
				targetModel.set(newAttributes);
			},

			cancelAction : function(event) {
				// alert("cancelAction");
			},

			showDataAction : function(event) {
				alert(this.model.toJSON());
			},

			changeTextAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				this.model.set(changeAttributeName, $currentTarget.val(), {
					validate : true,
					silent : true
				});
			},
			changeDateAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				var dateSplit = $currentTarget.val().match(/\d+/g);
				var newDate = new Date(Date.UTC(dateSplit[0], parseInt(dateSplit[1]) - 1, dateSplit[2]));
				this.model.set(changeAttributeName, newDate, {
					validate : true,
					silent : true
				});
			},
			changeTimeAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				var dateSplit = $currentTarget.val().match(/\d+/g);
				var newDate = new Date(Date.UTC(0, 0, 0, dateSplit[0], dateSplit[1], dateSplit[2]));
				this.model.set(changeAttributeName, newDate, {
					validate : true,
					silent : true
				});
				$currentTarget.val(this.model.get(changeAttributeName).formatUTC('hh:mm:ss'));
			},
			validateTimeAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var timeValue = $currentTarget.val();
				if (!/^\d{2}:\d{2}:\d{2}$/.test(timeValue)) {
					$currentTarget.val(this.model.get("breakfastTime").formatUTC('hh:mm:ss'));
					return false;
				}
			},
			changeFloatAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				this.model.set(changeAttributeName, parseFloat($currentTarget.val()), {
					validate : true,
					silent : true
				});
				$currentTarget.val(this.model.get(changeAttributeName));
			},
			changeIntAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				this.model.set(changeAttributeName, parseFloat($currentTarget.val()), {
					validate : true,
					silent : true
				});
				$currentTarget.val(this.model.get(changeAttributeName));
			},
			changeRadioAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				this.model.set(changeAttributeName, $currentTarget.val() === "true", {
					validate : true,
					silent : true
				});
			},
			changeCheckboxAction : function(event) {
				var $currentTarget = $(event.currentTarget);
				var changeAttributeName = $currentTarget.attr("name");
				var newLanguages = [];
				$("input[name='" + changeAttributeName + "']:checked", this.$contentPanel).each(function(index, element) {
					newLanguages.push($(element).val());
				});
				newLanguages.sort();
				this.model.set(changeAttributeName, newLanguages, {
					validate : true,
					silent : true
				});
			},
			modelValidateFailedListener : function(model, error, options) {
				this.render();
				alert("Model validate failed!\n" + error);
			}
		});

/**
 * page head view this view collection is batchUpdatePersonCollection
 */
PageHeadView = Backbone.View.extend({
	template : _.template("<span style='margin-left: 90%'></span><button type='button' disabled='disabled'>批量更新</button>"),

	events : {
		"click button" : "batchUpdateAction"
	},

	tagName : "p",

	initialize : function(options) {
		this.$el.css({
			width : "100%"
		});

		this.collection.on("add remove", this.updateBatchUpdatePersonCollectionListener, this);
	},
	render : function() {
		this.$el.html(this.template());
		return this;
	},
	updateBatchUpdatePersonCollectionListener : function(model, collection, options) {
		$("button", this.$el).prop("disabled", collection.length === 0);
	},
	batchUpdateAction : function(event) {
		if (confirm("是否要批量更新?")) {
			this.collection.batchUpdate({
				success : function(collection, resp, options) {
					var updateCount = _.filter(resp.actionStatus, function(actionStatus) {
						return actionStatus.processSuccessfully;
					}).length;
					alert("成功更新" + updateCount + "条记录!");
				}
			});
		}
	}
});

/**
 * page on load
 */
$().ready(function() {
	app.personCollection = new PersonCollection();
	app.batchUpdatePersonCollection = new BatchUpdatePersonCollection();

	var $personTable = $('#personTable').fixheadertable({
		caption : 'My employees',
		id : "personGrid",
		"class" : "customerClass",
		colratio : [ 50, 100, 80, 150, 150, 200, 50, 100, 80, 300, 50, 50, 200 ],
		height : 300,
		minWidthAuto : true,
		resizeCol : true,
		pager : true
	});

	// create edit person dialog
	$("#dialog-edit-person").dialog({
		autoOpen : false,
		width : 400,
		buttons : {
			"提交" : function() {
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			},
			"查看数据" : function() {
			}
		}
	});

	app.personTbodyView = new PersonTbodyView({
		collection : app.personCollection,
		el : $("#personGrid"),
		adaptScroll : $personTable.data("adaptScroll")
	});
	app.pageHeadView = new PageHeadView({
		collection : app.batchUpdatePersonCollection
	});
	$("#personGrid").before(app.pageHeadView.render().$el);

	app.modifyPersonview = new EditPersonFromView({
		el : $("#dialog-edit-person").parent(),
		model : new PersonModel()
	});
	
	$("#cacheable>:button").click(function(event){
		var $input=$(this).prev();
		$.getJSON(constant.servletPath+"/person/cacheable/"+$input.val(),function(data){
			alert(data.randomNum);
		});
	});
	
	$("#deleteCache>:button").click(function(event){
		var $input=$(this).prev();
		$.post(constant.servletPath+"/person/cacheable/"+$input.val(),function(data){
			alert(data.randomNum);
		},"json");
	});
	
	$("#deleteCacheAll>:button").click(function(event){
		$.post(constant.servletPath+"/person/cacheable",function(data){
			alert(data.randomNum);
		},"json");
	});
});
