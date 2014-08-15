// Used on index.html- gets scores for makes
function load() {
	$
			.ajax({
				type : "GET",
				async : false,
				url : "http://jelleyfishtrackers.com:8080/DbMakeServlet",
				data : {},
				dataType : "json",
				success : function(data) {
					if (data) {
						var ul = document.getElementById('Makes');
						for (var j = 0; j < data.length; j += 2) {
							var a = document.createElement('a');
							var link = "http://jelleyfishtrackers.com:8080/modelsPage.html?Something="
									+ data[j] + "";
							a.setAttribute("href", link);
							var li = document.createElement('li');
							li.setAttribute("class", "list-group-item");
							if (j + 1 < data.length - 1)
								li.setAttribute("style",
										"border-bottom-width: 0px");
							li.innerHTML = data[j];
							var span = document.createElement('span');
							span.setAttribute("class", "badge");
							var div = document.createElement('div');
							div.setAttribute("id", data[j]);
							if(data[j+1]===-1)
								alert('Our Twitter server is overwhelmed with calls right now! Try again in a couple minutes.')
							div.innerHTML = data[j + 1];
							span.appendChild(div);
							li.appendChild(span);
							a.appendChild(li);
							ul.appendChild(a);
						}
					}
				},
				error : function() {
					alert('ERROR: Not able to find the data from your search.');
				}
			});
}


function loadMakes() {
	$
			.ajax({
				type : "GET",
				async : false,
				url : "http://jelleyfishtrackers.com:8080/DbMakeServlet",
				data : {},
				dataType : "json",
				success : function(data) {
					if (data) {
						var ul = document.getElementById('Makes');
						
						for (var j = 0; j < data.length; j += 2) {
							var li = document.createElement('li');
							li.setAttribute("class", "list-group-item");
							if (j + 1 < data.length - 1)
								li.setAttribute("style","border-bottom-width: 0px");
							li.innerHTML = data[j];
							ul.appendChild(li);
							$('#select').empty();
							var newSelect = document.getElementById('select');
							
							for (var i = 0; i < j + 1; i += 2) {
								var opt = document.createElement("option");
								opt.value = data[i];
								newSelect.appendChild(opt);
								opt.innerHTML = data[i];
							}
						}
					}
				},
				error : function() {
					alert('ERROR: Not able to Delete Term.');
				}
			});
}

// Used on modelsPage.html - gets models with scores
function loadModels() {
	var make = getParam('Something');
	$
			.ajax({
				type : "GET",
				async : false,
				url : "http://jelleyfishtrackers.com:8080/DbModelServlet",
				data : {
					"make" : make
				},
				dataType : "json",
				success : function(data) {
					if (data) {
						if (data.length === 0) {
							var ul = document.getElementById('Models');
							var title = document.getElementById('makeName');
							title.innerHTML = make;
							var li = document.createElement('li');
							li.innerHTML = ("Sorry, " + make + " does not have any models in our records.");
							li.setAttribute("style", "text-align:center")
							ul.appendChild(li);
						} else {
							var ul = document.getElementById('Models');
							var title = document.getElementById('makeName');
							$('#makeName').empty();
							$('#directions').empty();
							title.innerHTML = make;
							$('#Models').empty();
							for (var j = 0; j < data.length; j += 2) {

								var li = document.createElement('li');
								li.setAttribute("class", "list-group-item");
								if (j + 1 < data.length - 1)
									li.setAttribute("style",
											"border-bottom-width: 0px");
								li.innerHTML = data[j];
								var span = document.createElement('span');
								span.setAttribute("class", "badge");
								var div = document.createElement('div');
								div.setAttribute("id", data[j]);
								div.innerHTML = data[j + 1];
								span.appendChild(div);
								li.appendChild(span);
								ul.appendChild(li);
							}
						}
					}
				},
				error : function() {
					alert('ERROR: Not able to find the data from your search.');
				}
			});
}

function addCar() {
	var cars = document.forms["myForm"]["searchInput"].value;
	var searchAlias=document.forms["myForm"]["searchNameInput"].value;
	$.ajax({
		type : "GET",
		async : false,
		url : "http://jelleyfishtrackers.com:8080/DbEditorServlet",
		data : {
			"car" : cars,
			"searchName":searchAlias,
			"type" : "insert"
		},
		dataType : "json",
		success : function() {
		},
		error : function() {
			$('#Makes').empty();
			loadMakes();
		}
	})
}
function subtractCar(){
	var cars = document.forms["deleteForm"]["searchInput"].value;
	
	$.ajax({
				type : "GET",
				async: false,
				url : "http://jelleyfishtrackers.com:8080/DbEditorServlet",
				data : {
					"car" : cars,
					"type" : "delete"
				},
				dataType : "json",
				success : function() {
				},
				error : function() {
					$('#Makes').empty();
					loadMakes();
				}
			})
}



// Used on SearchPage.html- adds search resluts to page
function performSearch() {

	$('#searchResults').empty();
	var cars = document.forms["myForm"]["searchInput"].value;
	var list = document.createElement("ul");
	list.className = "list-group";
	document.getElementById('searchResults').appendChild(list);
	var listItem;

	$.ajax({
		type : "GET",
		async : false,
		url : "http://jelleyfishtrackers.com:8080/TwitterSearchServlet",
		data : {
			"car" : cars
		},
		dataType : "json",

		success : function(data) {
			if (data) {
				for (var i = 0; i < data.length; i++) {
					if (i % 4 === 0) {
						listItem = document.createElement('li');
						listItem.className = "list-group-item";
						list.appendChild(listItem);
						b = document.createElement('b');
						listItem.appendChild(b);
						b.innerHTML = ""+((i+4)/4)+". "+data[i];
					} else if (i % 4 === 1) {
						var p = document.createElement('p');
						listItem.appendChild(p);
						p.innerHTML = data[i] + ", " + data[i + 1] + ",    "
								+ data[i + 2];
					}

				}
			}
		},
		error : function(data) {
			alert('ERROR: Not able to find the data from your search. Your search term may not return any results.');
		}
	})

	$.ajax({
		type : "GET",
		url : "http://jelleyfishtrackers.com:8080/TwitterScoreServlet",
		data : {
			"car" : cars
		},
		dataType : "json",
		success : function(data) {
			if (data) {
				var b = document.createElement('b');
				document.getElementById('Score').appendChild(b);
				b.innerHTML = "Score: " + data;
			}
		},
		error : function() {
			alert('ERROR: Not able to find the data from your search.');

		}
	})
};

function getParam(name) {
	var start = location.search.indexOf("?" + name + "=");
	if (start < 0)
		start = location.search.indexOf("&" + name + "=");
	if (start < 0)
		return '';
	start += name.length + 2;
	var end = location.search.indexOf("&", start) - 1;
	if (end < 0)
		end = location.search.length;
	var result = '';
	for (var i = start; i <= end; i++) {
		var c = location.search.charAt(i);
		result = result + (c == '+' ? ' ' : c);
	}
	return unescape(result);
}
