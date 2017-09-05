/**
 * Member javaScript
 */
var app=(function(){
   var init=function(ctx){
   session.init(ctx);
   member.init();
   onCreate();
   };
   var onCreate=function(){
      setContentView();
      location.href=ctx()+"/home.do";
   };
   var setContentView=function(){
   };
   var ctx=function(){
      return session.getPath('ctx');
   };
   var js=function(){
      return session.getPath('js');
   };
   var img=function(){
      return session.getPath('img');
   };
   var css=function(){
      return session.getPath('css');
   };
   return{
      init : init,
      ctx : ctx,
      js : js,
      img : img,
      css : css
   }
})();

var session=(function(){
   var init=function(ctx){
   sessionStorage.setItem('ctx',ctx);
   sessionStorage.setItem('js',ctx+'/resorces/js');
   sessionStorage.setItem('img',ctx+'/resorces/img');
   sessionStorage.setItem('css',ctx+'/resorces/css');
   };
   var getPath=function(x){
      return sessionStorage.getItem(x);
   };
   return {
      init : init,
      getPath : getPath
   }
})();


var member=(function(){
   var init=function(){
      $('#loginBtn').on('click',function(){
         alert('로그인 실행');
         if($('#input_id').val()===""){
            alert('ID를 입력해 주세요');
            return false;
         }
         if($('#input_pass').val()===""){
            alert('PASSWORD를 입력해 주세요');
            return false;
         }
         $('#login-box').attr('action',app.ctx()+"/common.do");
         $('#login-box').attr('method','post');
         return true;
      });
   };
   var mainLoad=function(){};
      
   return {
      init : init
   
   }
})();

var main=(function(){
   var init=function(){
      onCreate();
   };
   var onCreate=function(){
      setContentView();
      
      $('.list-group li').eq(0).on('click',function(){
         controller.moveTo('member','member_add');
      });
      $('.list-group li').eq(1).on('click',function(){
         controller.list('member','member_list', '1');
      });
      $('.list-group li').eq(2).on('click',function(){
         controller.detailStudent(prompt('조회할 id'));
      });
      $('.list-group li').eq(3).on('click',function(){
         controller.moveTo('member','member_update');
      });
      $('.list-group li').eq(4).on('click',function(target){
         controller.deleteTarget('target');
      });
   };
   var setContentView=function(){      
      var $u1=$('#main_ul_stu');
       var $u2=$('#main_ul_grade');
       var $u3=$('#main_ul_board');
       
    $u1.addClass("list-group");
    $u2.addClass("list-group");
    $u3.addClass("list-group");
   
    $('.list-group').children().addClass("list-group-item");
   };
   
   return {
      init : init
   }
})();

var navbar=(function(){
   //홈
   var init=function(){
      onCreate();
   };
   
   var onCreate=function(){
      setContentView();
      $('#main-go').on('click',function(){
          location.href=app.ctx()+"/common.do?action=move&page=main";
       });
       $('#logout').on('click',function(){
          controller.logout('common','home');
       });
       $('.dropdown-menu a').eq(0).on('click',function(dir,page){
          controller.moveTo('member','member_add');
       });
       $('.dropdown-menu a').eq(1).on('click',function(dir,page,pageNumber){
          controller.list('member','member_list', '1');
       });
       $('.dropdown-menu a').eq(2).on('click',function(){
    	   controller.detailStudent(prompt('조회할 id'));
       });
       $('.dropdown-menu a').eq(3).on('click',function(target){
          controller.deleteTarget('target');
       });
   };
   var setContentView=function(){
       var $u1=$("#navbar_drop_stu");
       var $u2=$("#navbar_drop_grade");
       var $u3=$("#navbar_drop_board");
       
       $u1.addClass("dropdown-menu");
       $u2.addClass("dropdown-menu");
       $u3.addClass("dropdown-menu");

   };
   
   return {
      init : init
      
   };
})();

var memberDetail=(function(){
   var init=function(){
      onCreate();
   };
   var onCreate=function(){
      $("#updateBtn").on('click',function(){
      
      setContentView();
   //   id,phone,email,title;
      sessionStorage.setItem('id',$("#detail_id").text());
      sessionStorage.setItem('phone',$("#detail_phone").text());
      sessionStorage.setItem('email',$("#detail_email").text());
      sessionStorage.setItem('title',$("#detail_title").text());
      alert($("#detail_phone").text());
      controller.moveTo('member','member_update');
     });
   };
      var setContentView=function(){
      
   };
   
   return{
      init : init,
   
      
   };
})();

var memberUpdate=(function(){
   var init=function(){
      onCreate();
   };
   var onCreate=function(){
      setContentView();
      
   };
   var setContentView=function(){
      var id=sessionStorage.getItem('id');
      var phone=sessionStorage.getItem('phone');
      var email=sessionStorage.getItem('email');
      var password=$('#confirm').val();
      $('#phone').attr('placeholder',phone);
      $('#email').attr('placeholder',email);
      $('#confirmBtn').on('click',function(){
         controller.updateStudent(id,$('#email').val(),$('#phone').val());
         alert('수정할 내용'+id);
      });
   };
   return{
      init : init
   }
})();
var controller=(function(){
   var init=function(){

   };
   var moveTo=function(dir,page){
   location.href=
      app.ctx()+"/"+dir+".do?action=move&page="+page;
   };
   var deleteTarget= function(target){
       prompt(target+'의 ID?');
    }
   var list= function (dir,page,pageNumber){
      location.href=app.ctx()+"/"+dir+".do?action=list&page="+page+"&pageNumber="+pageNumber;
   }
   var logout=function (dir,page){   
      location.href=app.ctx()+"/"+dir+".do?action=move&page="+page;
      return true;
      }   
   var updateStudent=function (id,email,phone){
        alert('수정할 id'+$("#detail_id").text());
        location.href=app.ctx()+"/member.do?action=update&page=member_update&id="+id+"&email="+email+"&phone="+phone;
     }
     var deleteStudent=function (id){
        alert('삭제할id'+id);
        location.href=app.ctx()+"/member.do?action=delete&page=member_list&id="+id;
     }
     var detailStudent=function (id){
        alert('조회할 id'+id);
        location.href=app.ctx()+"/member.do?action=detail&page=member_detail&id="+id;
     }
     var searchStudent=function (){
        var search=$('#searchName').value;
        alert(search);
        location.href=app.ctx()+"/member.do?action=search&page=member_list&search="+search;   
     } 
   return {
      init : init,
      moveTo : moveTo,
      deleteTarget : deleteTarget,
      list : list,
      logout : logout,
      updateStudent : updateStudent,
      deleteStudent : deleteStudent,
      detailStudent : detailStudent,
      searchStudent : searchStudent
   }
})();


var home=(function(){
   var init=function(){
      onCreate();
   };
   var onCreate=function(){
      setContentView();
   };
   var setContentView=function(){
      };
   return {
      init : init
   
   }
})();

 




