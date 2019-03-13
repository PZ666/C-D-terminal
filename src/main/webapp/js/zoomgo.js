/*
 * Zoomgo.net Co., Ltd
 * 
 */

function thousandBitSeparator(num) {
    return num && num
        .toString()
        .replace(/(\d)(?=(\d{3})+\.)/g, function($0, $1) {
            return $1 + ",";
        });
}

function timeFormat(nS) {     
	  return new Date(nS).toLocaleString();     
	};

function refreshPage(dataPu,inPageBtn){
	if(dataPu.pageRecorders == 20){
		 $('#btn10').removeClass("active btn-primary");
		 $('#btn10').addClass("btn-white");
		 $('#btn15').removeClass("active btn-primary");
		 $('#btn15').addClass("btn-white");
		 $('#btn20').addClass("active btn-primary");
		 $('#btn20').removeClass("btn-white");
	}else if(dataPu.pageRecorders == 15){
		 $('#btn10').removeClass("active btn-primary");
		 $('#btn10').addClass("btn-white");
		 $('#btn20').removeClass("active btn-primary");
		 $('#btn20').addClass("btn-white");
		 $('#btn15').addClass("active btn-primary");
		 $('#btn15').removeClass("btn-white");
	}else{
		 $('#btn15').removeClass("active btn-primary");
		 $('#btn15').addClass("btn-white");
		 $('#btn20').removeClass("active btn-primary");
		 $('#btn20').addClass("btn-white");
		 $('#btn10').addClass("active btn-primary");
		 $('#btn10').removeClass("btn-white");
	}
	
	$('.pageBtn').removeClass("active");
	$("#btnPage"+inPageBtn).addClass("active");
	
	var pageHtmls = "";
	if(dataPu.currentPage != 1){
		 pageHtmls += '<button onclick="nextUserList(-1)" class="btn btn-white"><i class="fa fa-chevron-left"></i>&nbsp;</button>';
	}
	if(dataPu.currentPage > 4){
		 pageHtmls += '<button onclick="selectUserList(1,0)" id="btnPage1" class="pageBtn btn btn-white">1</button>';
			 pageHtmls += '<button class="btn btn-white" disabled="">---</button>';
	}
	if(dataPu.currentPage < 4){
		 var endPagesNb = dataPu.totalPages;
		 if(dataPu.totalPages > 7){
			 endPagesNb = 7;
		 }
		 for(var i = 0 ; i < endPagesNb ; i++){
			 pageHtmls += '<button onclick="selectUserList('+(i+1)+',0)" id="btnPage'+(i+1)+'" class="pageBtn btn btn-white';
			 if(dataPu.currentPage == (i+1)){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(i+1)+'</button>';
		 }
	}
	if(dataPu.currentPage >= 4 && dataPu.totalPages - dataPu.currentPage >= 4){
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage-3)+',0)" id="btnPage'+(dataPu.currentPage-3)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage-3)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage-2)+',0)" id="btnPage'+(dataPu.currentPage-2)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage-2)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage-1)+',0)" id="btnPage'+(dataPu.currentPage-1)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage-1)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage)+',0)" id="btnPage'+(dataPu.currentPage)+'" class="pageBtn btn btn-white active">'+(dataPu.currentPage)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage+1)+',0)" id="btnPage'+(dataPu.currentPage+1)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage+1)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage+2)+',0)" id="btnPage'+(dataPu.currentPage+2)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage+2)+'</button>';
		 pageHtmls += '<button onclick="selectUserList('+(dataPu.currentPage+3)+',0)" id="btnPage'+(dataPu.currentPage+3)+'" class="pageBtn btn btn-white">'+(dataPu.currentPage+3)+'</button>';
	}
	if(dataPu.currentPage >= 4 && dataPu.totalPages - dataPu.currentPage < 4){
		 if(dataPu.totalPages-6 > 0){
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-6)+',0)" id="btnPage'+(dataPu.totalPages-6)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-6){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-6)+'</button>';
		 }
		 if(dataPu.totalPages-5 > 0){
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-5)+',0)" id="btnPage'+(dataPu.totalPages-5)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-5){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-5)+'</button>';
		 }
		 if(dataPu.totalPages-4 > 0){
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-4)+',0)" id="btnPage'+(dataPu.totalPages-4)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-4){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-4)+'</button>';
		 }
		 if(dataPu.totalPages-3 > 0){
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-3)+',0)" id="btnPage'+(dataPu.totalPages-3)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-3){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-3)+'</button>';
		 }
		 if(dataPu.totalPages-2 > 0){
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-2)+',0)" id="btnPage'+(dataPu.totalPages-2)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-2){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-2)+'</button>';
		 }
		 if(dataPu.totalPages-1 > 0){				
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages-1)+',0)" id="btnPage'+(dataPu.totalPages-1)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages-1){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages-1)+'</button>';
		 }
		 if(dataPu.totalPages > 0){		
			 pageHtmls += '<button onclick="selectUserList('+(dataPu.totalPages)+',0)" id="btnPage'+(dataPu.totalPages)+'" class="pageBtn btn btn-white ';
			 if(dataPu.currentPage == dataPu.totalPages){
				 pageHtmls += 'active';
			 }
			 pageHtmls += '">'+(dataPu.totalPages)+'</button>';
		 }
	}
	
	
	
	if(dataPu.totalPages - dataPu.currentPage >= 4){
		 pageHtmls += '<button class="btn btn-white" disabled="">---</button>';
		 pageHtmls += '<button onclick="selectUserList('+dataPu.totalPages+',0)" id="btnPage'+dataPu.totalPages+'" class="pageBtn btn btn-white">'+dataPu.totalPages+'</button>';
	}
	if(dataPu.totalPages != dataPu.currentPage){
		 pageHtmls += '<button onclick="nextUserList(1)" class="btn btn-white">&nbsp;<i class="fa fa-chevron-right"></i></button>';
	}
	$('#pageNumberBtns').html(pageHtmls);
}


$(document).ready(function () {
	
	
//	$("#loadingModal").on("hidden.bs.modal",function(){ 
//		  $(document.body).addClass("modal-open");
//	});  
//
//	$(".inmodal").on("hidden.bs.modal",function(){  
//		  $(document.body).css("padding-right","0px");
//	});  
	
    // Add body-small class if window less than 768px
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }

    // MetsiMenu
    $('#side-menu').metisMenu();

    // Collapse ibox function
    $('.collapse-link').click(function () {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.find('div.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function () {
            ibox.resize();
            ibox.find('[id^=map-]').resize();
        }, 50);
    });

    // Close ibox function
    $('.close-link').click(function () {
        var content = $(this).closest('div.ibox');
        content.remove();
    });

    // Fullscreen ibox function
    $('.fullscreen-link').click(function () {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        $('body').toggleClass('fullscreen-ibox-mode');
        button.toggleClass('fa-expand').toggleClass('fa-compress');
        ibox.toggleClass('fullscreen');
        setTimeout(function () {
            $(window).trigger('resize');
        }, 100);
    });

    // Close menu in canvas mode
    $('.close-canvas-menu').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    // Run menu of canvas
    $('body.canvas-menu .sidebar-collapse').slimScroll({
        height: '100%',
        railOpacity: 0.9
    });

    // Open close right sidebar
    $('.right-sidebar-toggle').click(function () {
        $('#right-sidebar').toggleClass('sidebar-open');
    });

    // Initialize slimscroll for right sidebar
    $('.sidebar-container').slimScroll({
        height: '100%',
        railOpacity: 0.4,
        wheelStep: 10
    });

    // Open close small chat
    $('.open-small-chat').click(function () {
        $(this).children().toggleClass('fa-comments').toggleClass('fa-remove');
        $('.small-chat-box').toggleClass('active');
    });

    // Initialize slimscroll for small chat
    $('.small-chat-box .content').slimScroll({
        height: '234px',
        railOpacity: 0.4
    });

    // Small todo handler
    $('.check-link').click(function () {
        var button = $(this).find('i');
        var label = $(this).next('span');
        button.toggleClass('fa-check-square').toggleClass('fa-square-o');
        label.toggleClass('todo-completed');
        return false;
    });

    // Append config box / Only for demo purpose
    // Uncomment on server mode to enable XHR calls
//    $.get("skin-config.html", function (data) {
//        if (!$('body').hasClass('no-skin-config'))
//            $('body').append(data);
//    });

    // Minimalize menu
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();

    });

    // Tooltips demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    // Move modal to body
    // Fix Bootstrap backdrop issu with animation.css
    $('.modal').appendTo("body");

    // Full height of sidebar
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");

        var navbarHeigh = $('nav.navbar-default').height();
        var wrapperHeigh = $('#page-wrapper').height();

        if (navbarHeigh > wrapperHeigh) {
            $('#page-wrapper').css("min-height", navbarHeigh + "px");
        }

        if (navbarHeigh < wrapperHeigh) {
            $('#page-wrapper').css("min-height", $(window).height() + "px");
        }

        if ($('body').hasClass('fixed-nav')) {
            if (navbarHeigh > wrapperHeigh) {
                $('#page-wrapper').css("min-height", navbarHeigh - 60 + "px");
            } else {
                $('#page-wrapper').css("min-height", $(window).height() - 60 + "px");
            }
        }

    }

    fix_height();

    // Fixed Sidebar
    $(window).bind("load", function () {
        if ($("body").hasClass('fixed-sidebar')) {
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9
            });
        }
    });

    // Move right sidebar top after scroll
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
            $('#right-sidebar').addClass('sidebar-top');
        } else {
            $('#right-sidebar').removeClass('sidebar-top');
        }
    });

    $(window).bind("load resize scroll", function () {
        if (!$("body").hasClass('body-small')) {
            fix_height();
        }
    });

    $("[data-toggle=popover]")
        .popover();

    // Add slimscroll to element
    $('.full-height-scroll').slimscroll({
        height: '100%'
    })
});


// Minimalize menu when screen is less than 768px
$(window).bind("resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

// Local Storage functions
// Set proper body class and plugins based on user configuration
$(document).ready(function () {
    if (localStorageSupport) {

        var collapse = localStorage.getItem("collapse_menu");
        var fixedsidebar = localStorage.getItem("fixedsidebar");
        var fixednavbar = localStorage.getItem("fixednavbar");
        var boxedlayout = localStorage.getItem("boxedlayout");
        var fixedfooter = localStorage.getItem("fixedfooter");

        var body = $('body');

        if (fixedsidebar == 'on') {
            body.addClass('fixed-sidebar');
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9
            });
        }

        if (collapse == 'on') {
            if (body.hasClass('fixed-sidebar')) {
                if (!body.hasClass('body-small')) {
                    body.addClass('mini-navbar');
                }
            } else {
                if (!body.hasClass('body-small')) {
                    body.addClass('mini-navbar');
                }

            }
        }

        if (fixednavbar == 'on') {
            $(".navbar-static-top").removeClass('navbar-static-top').addClass('navbar-fixed-top');
            body.addClass('fixed-nav');
        }

        if (boxedlayout == 'on') {
            body.addClass('boxed-layout');
        }

        if (fixedfooter == 'on') {
            $(".footer").addClass('fixed');
        }
    }
});

// check if browser support HTML5 local storage
function localStorageSupport() {
    return (('localStorage' in window) && window['localStorage'] !== null)
}

// For demo purpose - animation css script
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //wait for animation to finish before removing classes
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 200);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 100);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
}

// Dragable panels
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable(
        {
            handle: handle,
            connectWith: connect,
            tolerance: 'pointer',
            forcePlaceholderSize: true,
            opacity: 0.8
        })
        .disableSelection();
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


/** 
* 去掉字符串头尾空格 
* @param str 传入的字符串值 
* @author lqy 
* @since 2015-08-21 
*/  
function trim(str) {  
    if(str == null || typeof str == "undefined"){  
        return "";  
    }  
    return str.replace(/(^\s*)|(\s*$)/g, "");  
};  
  
/** 
 * 是否为Null 
 * @param object 
 * @returns {Boolean} 
 */  
function isNull(object){  
    if(object == null || typeof object == "undefined"){  
        return true;  
    }  
    return false;  
};  
  
/** 
 * 是否为空字符串，有空格不是空字符串 
 * @param str 
 * @returns {Boolean} 
 */  
function isEmpty(str){  
    if(str == null || typeof str == "undefined" ||   
            str == ""){  
        return true;  
    }  
    return false;  
};  
  
/** 
 * 是否为空字符串，全空格也是空字符串 
 * @param str 
 * @returns {Boolean} 
 */  
function isBlank(str){  
    if(str == null || typeof str == "undefined" ||   
            str == "" || trim(str) == ""){  
        return true;  
    }  
    return false;  
};  



function Map() {
    this.elements = new Array();
    // 获取Map元素个数
    this.size = function() {
        return this.elements.length;
    },
    // 判断Map是否为空
    this.isEmpty = function() {
        return (this.elements.length < 1);
    },
    // 删除Map所有元素
    this.clear = function() {
        this.elements = new Array();
    },
    // 向Map中增加元素（key, value)
    this.put = function(_key, _value) {
        if (this.containsKey(_key) == true) {
            if (this.containsValue(_value)) {
                if (this.remove(_key) == true) {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        } else {
            this.elements.push({
                key : _key,
                value : _value
            });
        }
    },
    // 向Map中增加元素（key, value)
    this.set = function(_key, _value) {
        if (this.containsKey(_key) == true) {
            if (this.containsValue(_value)) {
                if (this.remove(_key) == true) {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        } else {
            this.elements.push({
                key : _key,
                value : _value
            });
        }
    },
    // 删除指定key的元素，成功返回true，失败返回false
    this.remove = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },

    // 删除指定key的元素，成功返回true，失败返回false
    this.delete = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },
    
    // 获取指定key的元素值value，失败返回null
    this.get = function(_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    },

    // set指定key的元素值value
    this.setValue = function(_key, _value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements[i].value = _value;
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },

    // 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
    this.element = function(_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    },

    // 判断Map中是否含有指定key的元素
    this.containsKey = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },

    // 判断Map中是否含有指定key的元素
    this.has = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },
    
    // 判断Map中是否含有指定value的元素
    this.containsValue = function(_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    },

    // 获取Map中所有key的数组（array）
    this.keys = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    },

    // 获取Map中所有value的数组（array）
    this.values = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };
    
    /**
    * map遍历数组
    * @param callback [function] 回调函数；
    * @param context [object] 上下文；
    */
    this.forEach = function forEach(callback,context){
        context = context || window;
        
        //IE6-8下自己编写回调函数执行的逻辑
        var newAry = new Array();
        for(var i = 0; i < this.elements.length;i++) {
            if(typeof  callback === 'function') {
                var val = callback.call(context,this.elements[i].value,this.elements[i].key,this.elements);
                newAry.push(this.elements[i].value);
            }
        }
        return newAry;
    }

}

