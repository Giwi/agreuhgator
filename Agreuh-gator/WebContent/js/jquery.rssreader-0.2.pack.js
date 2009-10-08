(function($) {
	$.fn.rssReader = function(j) {
		var k = $.extend( {
			targeturl : "http://www.clashdesign.net/blog/index.php/feed/rss2",
			items : 5,
			Maxlength : 80,
			loadingImg : '35-1.gif'
		}, j);
		if (!j.targeturl)
			return false;
		var l = $.extend(k, j);
		var m = ($.browser.msie);
		if (m) {
			$('#newsfeed')
					.ajaxStart(
							function() {
								$("#newsfeed").css( {
									//backgroundColor : '#ccc',
									'z-index' : 90,
									'opacity' : 0.4
								});
								$(
										'<img id="loading" src="' + l.loadingImg + '" alt="Traitement en cours ..." />')
										.css( {
											'opacity' : 1
										}).appendTo('#newsfeed')
							});
			$("#newsfeed").ajaxError(function() {
				$(this).append("<strong>Error requesting page</strong>")
			});
			$.get(l.targeturl, function(f) {
				$('#newsfeed img').remove();
				$("#newsfeed").css( {
					backgroundColor : '#fff',
					'opacity' : 1
				});
				var i = 0;
				var g = j.items;
				function h(e) {
					$(f).find('item').each(
							function(i) {
								if (i > e - 1)
									return;
								var a = $(this).find('title').text();
								var b = $(this).find('pubDate').text();
								var c = $(this).find('link').text();
								var d = $(this).find('description').text();
								$('<a href="' + c + '">' + a + '</a>').html(a)
										.appendTo('#newsfeed');
								$('<div class="date">' + b + '</div>').html(b)
										.appendTo('#newsfeed');
								$(
										'<div class="description">' + o(d,
												j.Maxlength) + '</div>').html(
										o(d, j.Maxlength) + '...').appendTo(
										'#newsfeed');
								$('#newsfeed .description p img').remove();
								$(
										'<div class="read_more_link"><a  href="' + c
												+ '">' + "En savoir plus"
												+ '</a></div><br />').appendTo(
										'#newsfeed')
							})
				}
				return h(g)
			})
		} else {
			var n = "xml";
			$
					.ajax( {
						type : "get",
						url : l.targeturl,
						dataType : n,
						processData : false,
						beforeSend : function() {
							$("#newsfeed").css( {
							//	backgroundColor : '#ccc',
								'z-index' : 90,
								'opacity' : 0.4
							});
							$(
									'<img id="loading" src="' + l.loadingImg + '" alt="Traitement en cours ..." />')
									.css( {
										'opacity' : 1
									}).appendTo('#newsfeed')
						},
						success : function(f) {
							$("#newsfeed").css( {
							//	backgroundColor : '#fff',
								'opacity' : 1
							});
							$('#newsfeed img').remove();
							var i = 0;
							var g = j.items;
							function h(e) {
								$(f)
										.find('item')
										.each(
												function(i) {
													if (i > e - 1)
														return;
													var a = $(this).find(
															'title').text();
													var b = $(this).find(
															'pubDate').text();
													var c = $(this)
															.find('link')
															.text();
													var d = $(this).find(
															'description')
															.text();
													$(
															'<a class="targetblank" href="'
																	+ c + '">'
																	+ a
																	+ '</a>')
															.html(a)
															.appendTo(
																	'#newsfeed');
													$(
															'<div class="date">' + b + '</div>')
															.html(b)
															.appendTo(
																	'#newsfeed');
													$(
															'<div class="description">' + o(
																	d,
																	j.Maxlength) + '</div>')
															.html(
																	o(
																			d,
																			j.Maxlength) + '...')
															.appendTo(
																	'#newsfeed');
													$(
															'#newsfeed .description p img')
															.remove();
													$(
															'<div class="read_more_link"><a  href="'
																	+ c
																	+ '">'
																	+ "En savoir plus"
																	+ '</a></div><br />')
															.appendTo(
																	'#newsfeed')
												})
							}
							return h(g)
						}
					})
		}
	};
	function o(a, b) {
		var c = a.split(/\s/);
		if (c.length <= b)
			return a;
		var d = '';
		for ( var i = 0; i < b; i++) {
			d += c[i] + ' '
		}
		return d
	}
})(jQuery);