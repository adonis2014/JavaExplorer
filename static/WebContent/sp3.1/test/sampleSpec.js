/**
 * Fake Request
 */
describe("Fake XML http request test", function() {
	var reponseStack=[];
	
	beforeEach(function() {
		this.xhr = sinon.useFakeXMLHttpRequest();
        this.xhr.onCreate = function (xhr) {
        	reponseStack.push(xhr);
        };
	});

	afterEach(function(){
		this.xhr.restore();
	});
	
	it("test simple ajax request", function() {
		var responseData={id:9,name:"tsetName",age:23,comment:"测试数据"};
		$.getJSON("",{aa:12},function(data,textStatus, jqXHR){
			expect(textStatus).toEqual("success");
			expect(data).toEqual(responseData);
			expect(jqXHR.status).toBe(200);
		});
		reponseStack.pop().respond(200, { "Content-Type": "application/json" },
        JSON.stringify(responseData));
	});

});

/**
 * Fake Server
 */
describe("Fake Server test", function() {
	var responseData={id:9,name:"tsetName",age:23,comment:"测试数据"};
	
	beforeEach(function() {
		this.server = sinon.fakeServer.create();
		this.server.respondWith("GET", "/some/article/comments.json",
                [200, { "Content-Type": "application/json" },
                 JSON.stringify(responseData)]);
	});

	afterEach(function(){
		this.server.restore();
	});
	
	it("test simple service request", function() {
		$.getJSON("/some/article/comments.json",$.proxy(function(data,textStatus, jqXHR){
			expect(textStatus).toEqual("success");
			expect(data).toEqual(responseData);
			expect(jqXHR.status).toBe(200);
		},this));
		this.server.respond();
	});

});


/**
 * Test Underscore 
 */
describe("Test underscore function", function() {
	it("test collection find", function() {
		var list=[1,2,3,4];
		var result =_.find(list,function(element){
			return element===2;
		});
		expect(result).not.toBeNull();
		expect(result).toBe(2);
		
		result =_.find(list,function(element){
			return false;
		});
		expect(result).toBeUndefined();
	});
});