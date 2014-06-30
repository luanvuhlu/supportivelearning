
function handleLoginRequest(xhr, status, args) {  
        if(args.validationFailed || !args.loggedIn) {  
            PF('dlg').jq.effect("shake", { times:5 }, 100);
        }   
        else {  
            PF('dlg').hide();  
            $(".account-link").slideUp();
            if(args.role ==  "user"){
                showRoleChooserDialog();
            }else{
                window.location.reload();
            }
        }  
    } 
    function showRoleChooserDialog(){
        $("#login-dialog").load("role-chooser-dialog.xhtml", function (){
            PF('role-chooseer-dialog').show();
        })
    }
    
    function handleDialogRequest(xhr, status, args, dlg) {
            if (args.validationFailed || !args.update) {
                PF(dlg).jq.effect("shake", {times: 5}, 100);
            } else {
                PF(dlg).hide();
            }
        }
    
    
    
    
    
    
//
//	jQuery Slug Generation Plugin by Perry Trinier (perrytrinier@gmail.com)
//  Licensed under the GPL: http://www.gnu.org/copyleft/gpl.html

jQuery.fn.slug = function(options) {
	var settings = {
		slug: 'slug', // Class used for slug destination input and span. The span is created on $(document).ready() 
		hide: true	 // Boolean - By default the slug input field is hidden, set to false to show the input field and hide the span. 
	};
	
	if(options) {
		jQuery.extend(settings, options);
	}
	
	$this = jQuery(this);

	jQuery(document).ready( function() {
		if (settings.hide) {
			jQuery('input.' + settings.slug).after("<span class="+settings.slug+"></span>");
			jQuery('input.' + settings.slug).hide();
		}
	});
	
	makeSlug = function() {
			var slugcontent = $this.val();
			var slugcontent_hyphens = slugcontent.replace(/\s/g,'-');
			var finishedslug = slugcontent_hyphens.replace(/[^a-zA-Z0-9\-]/g,'');
			jQuery('input.' + settings.slug).val(finishedslug.toLowerCase());
			jQuery('span.' + settings.slug).text(finishedslug.toLowerCase());

		}
		
	jQuery(this).keyup(makeSlug);
		
	return $this;
};