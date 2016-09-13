/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
	"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
	"sPaginationType": "bootstrap",
	"oLanguage": {
		"sLengthMenu": "_MENU_ records per page"
	}
} );


/* Default class modification */
$.extend( $.fn.dataTableExt.oStdClasses, {
	"sWrapper": "dataTables_wrapper form-inline"
} );


/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};


/* Bootstrap style pagination control */
$.extend( $.fn.dataTableExt.oPagination, {
	"bootstrap": {
		"fnInit": function( oSettings, nPaging, fnDraw ) {
			var oLang = oSettings.oLanguage.oPaginate;
			var fnClickHandler = function ( e ) {
				e.preventDefault();
				if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
					fnDraw( oSettings );
				}
			};

			$(nPaging).addClass('pagination').append(
				'<ul>'+
					'<li class="prev disabled"><a href="#">&larr; '+oLang.sPrevious+'</a></li>'+
					'<li class="next disabled"><a href="#">'+oLang.sNext+' &rarr; </a></li>'+
				'</ul>'
			);
			var els = $('a', nPaging);
			$(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
			$(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
		},

		"fnUpdate": function ( oSettings, fnDraw ) {
			var iListLength = 5;
			var oPaging = oSettings.oInstance.fnPagingInfo();
			var an = oSettings.aanFeatures.p;
			var i, ien, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

			if ( oPaging.iTotalPages < iListLength) {
				iStart = 1;
				iEnd = oPaging.iTotalPages;
			}
			else if ( oPaging.iPage <= iHalf ) {
				iStart = 1;
				iEnd = iListLength;
			} else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
				iStart = oPaging.iTotalPages - iListLength + 1;
				iEnd = oPaging.iTotalPages;
			} else {
				iStart = oPaging.iPage - iHalf + 1;
				iEnd = iStart + iListLength - 1;
			}

			for ( i=0, ien=an.length ; i<ien ; i++ ) {
				// Remove the middle elements
				$('li:gt(0)', an[i]).filter(':not(:last)').remove();

				// Add the new list items and their event handlers
				for ( j=iStart ; j<=iEnd ; j++ ) {
					sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
					$('<li '+sClass+'><a href="#">'+j+'</a></li>')
						.insertBefore( $('li:last', an[i])[0] )
						.bind('click', function (e) {
							e.preventDefault();
							oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
							fnDraw( oSettings );
						} );
				}

				// Add / remove disabled classes from the static elements
				if ( oPaging.iPage === 0 ) {
					$('li:first', an[i]).addClass('disabled');
				} else {
					$('li:first', an[i]).removeClass('disabled');
				}

				if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
					$('li:last', an[i]).addClass('disabled');
				} else {
					$('li:last', an[i]).removeClass('disabled');
				}
			}
		}
	}
} );


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ( $.fn.DataTable.TableTools ) {
	// Set the classes that TableTools uses to something suitable for Bootstrap
	$.extend( true, $.fn.DataTable.TableTools.classes, {
		"container": "DTTT btn-group",
		"buttons": {
			"normal": "btn",
			"disabled": "disabled"
		},
		"collection": {
			"container": "DTTT_dropdown dropdown-menu",
			"buttons": {
				"normal": "",
				"disabled": "disabled"
			}
		},
		"print": {
			"info": "DTTT_print_info modal"
		},
		"select": {
			"row": "active"
		}
	} );

	// Have the collection use a bootstrap compatible dropdown
	$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
		"collection": {
			"container": "ul",
			"button": "li",
			"liner": "a"
		}
	} );
}

/* Table initialisation */
$(document).ready(function() {
	var dataTable = $('#example').dataTable( {
		bFilter: false,
		bSort: false,
		bLengthChange: false,
		"bProcessing": false, // 是否显示取数据时的那个等待提示
	    "bServerSide": true,//这个用来指明是通过服务端来取数据
        "sAjaxSource": "/Examination/questions",//这个是请求的地址
        "fnServerData": retrieveData, // 获取数据的处理函数
        "aoColumnDefs": [
                         { "sWidth": "3.5%", "aTargets": [ 0 ] },
                         { "sWidth": "7.5%", "aTargets": [ 1 ] },
                         { "sWidth": "25%", "aTargets": [ 2 ] },
                         { "sWidth": "15%", "aTargets": [ 3 ] },
                         { "sWidth": "9.5%", "aTargets": [ 4 ] },
                         { "sWidth": "9.5%", "aTargets": [ 5 ] },
                         { "sWidth": "15%", "aTargets": [ 6 ] },
                         { "sWidth": "7.5%", "aTargets": [ 7 ] },
                         { "sWidth": "7.5%", "aTargets": [ 8 ] }
                       ]
		});
		$('#search_btn').click(function(){
			dataTable.fnDestroy(); 
			$('#example').dataTable( {
				bFilter: false,
				bSort: false,
				bLengthChange: false,
				"bProcessing": false, // 是否显示取数据时的那个等待提示
			    "bServerSide": true,//这个用来指明是通过服务端来取数据
		        "sAjaxSource": "/Examination/questions?number="+$('#search_form #number').val()
		        				+"&keywords="+$('#search_form #keywords').val()+"&type="+$('#search_form #type').val()
		        				+"&level="+$('#search_form #difficulty').val()+"&point="+$('#search_form #knowledge').val()
		        				+"&image="+$('#search_form #pic').val(),//这个是请求的地址
		        "fnServerData": retrieveData // 获取数据的处理函数
				});
		});
} );
function retrieveData( sSource111,aoData111, fnCallback111) {
	$.ajax({
		url : sSource111,//这个就是请求地址对应sAjaxSource
		data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(result) {
			fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
			AfterTable();
		},
		error : function(msg) {
		}
	});
};
function AfterTable(){
	// 选择对应的dlt-btn变化
	$('td input').each(function(){
		$(this).click(function(){
			if($(this).is(':checked')==true){
				$('#dlt-btn').attr('disabled',false);
			}else{
				$('#dlt-btn').attr('disabled',true);
			}
		});
	});
	  $("#hiddenIFrame").load(function(){
		    var wnd = this.contentWindow;
		    var str = $(wnd.document.body).html();
		    alert("成功！");
		    $('#myModal .close').click();
		    $('#editModal .close').click();
//		    $("#search_btn").click();
		  });
}