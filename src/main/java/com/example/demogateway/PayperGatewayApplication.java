package com.example.demogateway;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class PayperGatewayApplication {

@Resource
	private PaymentConfig config;

	@RequestMapping("/payment-required")
	@ResponseBody
	public ResponseEntity paymentRequired() {
		String content = "<head>\n" +
				"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n" +
				"  <script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>\n" +
				"  <link rel=\"stylesheet\" media=\"screen\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600\">\n" +
				"  <link rel=\"stylesheet\" media=\"screen\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\">\n" +
				"\n" +
				"\n" +
				"  <style>\n" +
				"    * {\n" +
				"         -moz-box-sizing:border-box;\n" +
				"         -webkit-box-sizing:border-box;\n" +
				"         box-sizing:border-box;\n" +
				"     }\n" +
				"\n" +
				"     html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre,\n" +
				"     abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp,\n" +
				"     small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li,\n" +
				"     fieldset, form, label, legend, caption, article, aside, canvas, details, figcaption, figure,  footer, header, hgroup,\n" +
				"     menu, nav, section, summary, time, mark, audio, video {\n" +
				"         margin:0;\n" +
				"         padding:0;\n" +
				"         border:0;\n" +
				"         outline:0;\n" +
				"         vertical-align:baseline;\n" +
				"         background:transparent;\n" +
				"     }\n" +
				"\n" +
				"     article, aside, details, figcaption, figure, footer, header, hgroup, nav, section {\n" +
				"         display: block;\n" +
				"     }\n" +
				"\n" +
				"     html {\n" +
				"         font-size: 16px;\n" +
				"         line-height: 24px;\n" +
				"         width:100%;\n" +
				"         height:100%;\n" +
				"         -webkit-text-size-adjust: 100%;\n" +
				"         -ms-text-size-adjust: 100%;\n" +
				"         overflow-y:scroll;\n" +
				"         overflow-x:hidden;\n" +
				"     }\n" +
				"\n" +
				"     img {\n" +
				"         vertical-align:middle;\n" +
				"         max-width: 100%;\n" +
				"         height: auto;\n" +
				"         border: 0;\n" +
				"         -ms-interpolation-mode: bicubic;\n" +
				"     }\n" +
				"\n" +
				"     body {\n" +
				"         min-height:100%;\n" +
				"         -webkit-font-smoothing: subpixel-antialiased;\n" +
				"     }\n" +
				"\n" +
				"     .clearfix {\n" +
				"\t       clear:both;\n" +
				"\t       zoom: 1;\n" +
				"     }\n" +
				"     .clearfix:before, .clearfix:after {\n" +
				"         content: \"\\0020\";\n" +
				"         display: block;\n" +
				"         height: 0;\n" +
				"         visibility: hidden;\n" +
				"     } \n" +
				"     .clearfix:after {\n" +
				"         clear: both;\n" +
				"     }\n" +
				"    </style>\n" +
				"  <style>\n" +
				"    .plain.error-page-wrapper {\n" +
				"    font-family: 'Source Sans Pro', sans-serif;\n" +
				"    background-color:#6355bc;\n" +
				"    padding:0 5%;\n" +
				"    position:relative;\n" +
				"  }\n" +
				"\n" +
				"  .plain.error-page-wrapper .content-container {\n" +
				"    -webkit-transition: left .5s ease-out, opacity .5s ease-out;\n" +
				"    -moz-transition: left .5s ease-out, opacity .5s ease-out;\n" +
				"    -ms-transition: left .5s ease-out, opacity .5s ease-out;\n" +
				"    -o-transition: left .5s ease-out, opacity .5s ease-out;\n" +
				"    transition: left .5s ease-out, opacity .5s ease-out;\n" +
				"    max-width:500px;\n" +
				"    position:relative;\n" +
				"    left:-30px;\n" +
				"    opacity:0;\n" +
				"  }\n" +
				"\n" +
				"  .plain.error-page-wrapper .content-container.in {\n" +
				"    left: 0px;\n" +
				"    opacity:1;\n" +
				"  }\n" +
				"\n" +
				"  .plain.error-page-wrapper .head-line {\n" +
				"    transition: color .2s linear;\n" +
				"    font-size:48px;\n" +
				"    line-height:60px;\n" +
				"    color:rgba(255,255,255,.2);\n" +
				"    letter-spacing: -1px;\n" +
				"    margin-bottom: 5px;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .subheader {\n" +
				"    transition: color .2s linear;\n" +
				"    font-size:36px;\n" +
				"    line-height:46px;\n" +
				"    color:#fff;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper hr {\n" +
				"    height:1px;\n" +
				"    background-color: rgba(255,255,255,.2);\n" +
				"    border:none;\n" +
				"    width:250px;\n" +
				"    margin:35px 0;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .context {\n" +
				"    transition: color .2s linear;\n" +
				"    font-size:18px;\n" +
				"    line-height:27px;\n" +
				"    color:#fff;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .context p {\n" +
				"    margin:0;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .context p:nth-child(n+2) {\n" +
				"    margin-top:12px;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .buttons-container {\n" +
				"    margin-top: 45px;\n" +
				"    overflow: hidden;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .buttons-container a {\n" +
				"    transition: color .2s linear, border-color .2s linear;\n" +
				"    font-size:14px;\n" +
				"    text-transform: uppercase;\n" +
				"    text-decoration: none;\n" +
				"    color:#fff;\n" +
				"    border:2px solid white;\n" +
				"    border-radius: 99px;\n" +
				"    padding:8px 30px 9px;\n" +
				"    display: inline-block;\n" +
				"    float:left;\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .buttons-container a:hover {\n" +
				"    background-color:rgba(255,255,255,.05);\n" +
				"  }\n" +
				"  .plain.error-page-wrapper .buttons-container a:first-child {\n" +
				"    margin-right:25px;\n" +
				"  }\n" +
				"\n" +
				"  @media screen and (max-width: 485px) {\n" +
				"    .plain.error-page-wrapper .header {\n" +
				"      font-size:36px;\n" +
				"     }\n" +
				"    .plain.error-page-wrapper .subheader {\n" +
				"      font-size:27px;\n" +
				"      line-height:38px;\n" +
				"     }\n" +
				"    .plain.error-page-wrapper hr {\n" +
				"      width:185px;\n" +
				"      margin:25px 0;\n" +
				"     }\n" +
				"\n" +
				"    .plain.error-page-wrapper .context {\n" +
				"      font-size:16px;\n" +
				"      line-height: 24px;\n" +
				"     }\n" +
				"    .plain.error-page-wrapper .buttons-container {\n" +
				"      margin-top:35px;\n" +
				"    }\n" +
				"\n" +
				"    .plain.error-page-wrapper .buttons-container a {\n" +
				"      font-size:13px;\n" +
				"      padding:8px 0 7px;\n" +
				"      width:45%;\n" +
				"      text-align: center;\n" +
				"    }\n" +
				"    .plain.error-page-wrapper .buttons-container a:first-child {\n" +
				"      margin-right:10%;\n" +
				"    }\n" +
				"  }\n" +
				"</style>\n" +
				"\n" +
				"  <style>\n" +
				"\n" +
				"    .background-color {\n" +
				"      background-color: #28092F !important;\n" +
				"    }\n" +
				"\n" +
				"\n" +
				"    .primary-text-color {\n" +
				"      color: #FFFFFF !important;\n" +
				"    }\n" +
				"\n" +
				"    .secondary-text-color {\n" +
				"      color: rgb(122, 59, 130) !important !important;\n" +
				"    }\n" +
				"\n" +
				"\n" +
				"  .logo {\n" +
				"    max-height: 50px;\n" +
				"    margin-bottom: 20px;\n" +
				"  }\n" +
				"\n" +
				"\n" +
				"\n" +
				"    .border-button {\n" +
				"      color: #FFFFFF !important;\n" +
				"      border-color: #FFFFFF !important;\n" +
				"    }\n" +
				"    .button {\n" +
				"      background-color: #FFFFFF !important;\n" +
				"      color:  !important;\n" +
				"    }\n" +
				"\n" +
				"\n" +
				"</style>\n" +
				"\n" +
				"<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script><link type=\"text/css\" href=\"https://cdn.cookielaw.org/skins/3.6.24/default_flat_bottom_two_button_black/v2/css/optanon.css\" rel=\"stylesheet\"><style>#optanon ul#optanon-menu li { background-color:  !important }#optanon ul#optanon-menu li.menu-item-selected { background-color:  !important }#optanon #optanon-popup-wrapper .optanon-white-button-middle { background-color: #FFAB00 !important }.optanon-alert-box-wrapper .optanon-alert-box-button-middle { background-color: #FFAB00 !important; border-color: #FFAB00 !important; }#optanon #optanon-popup-wrapper .optanon-white-button-middle a { color: #ffffff !important }.optanon-alert-box-wrapper .optanon-alert-box-button-middle a { color: #ffffff !important }#optanon #optanon-popup-bottom { background-color: #1855C5 !important }#optanon.modern #optanon-popup-top, #optanon.modern #optanon-popup-body-left-shading { background-color: #1855C5 !important }.optanon-alert-box-wrapper { background-color:#172B4D !important }.optanon-alert-box-wrapper .optanon-alert-box-bg p { color:#FFFFFF !important }#optanon, #optanon *, #optanon div, #optanon span, #optanon ul, #optanon li, #optanon a, #optanon p, .optanon-alert-box-wrapper * {\n" +
				"font-family: LLCircularWeb,\"Helvetica Neue\",Helvetica,sans-serif;\n" +
				"}\n" +
				"\n" +
				"#optanon #optanon-popup-body h2 {\n" +
				"    position: absolute;\n" +
				"    top: -55px;\n" +
				"    font-size: 16px;\n" +
				"    font-weight: bold;\n" +
				"    color: rgb(255, 255, 255);\n" +
				"    margin: 5px 0px 0px 5px;\n" +
				"}\n" +
				"#optanon #optanon-popup-wrapper .optanon-white-button-middle a {\n" +
				"    color: #000000 !important; \n" +
				"}\n" +
				".optanon-alert-box-wrapper .optanon-alert-box-button-middle a {\n" +
				"    color: #000000 !important;\n" +
				"}\n" +
				".optanon-alert-box-wrapper a {\n" +
				"    color: #ffffff !important; \n" +
				"    text-indent: 1;\n" +
				"   font-weight: bold; \n" +
				"  \n" +
				"}\n" +
				"\n" +
				".optanon-alert-box-button-middle, #optanon #optanon-popup-wrapper .optanon-white-button-middle {\n" +
				"   border-radius: 4px;\n" +
				"}\n" +
				".optanon-alert-box-wrapper {\n" +
				"    margin: 4px;\n" +
				"    border-radius: 4px;\n" +
				"    width: 99.5%;\n" +
				"}\n" +
				"\n" +
				"#optanon.modern #optanon-popup-top, #optanon.modern #optanon-popup-body-left-shading\n" +
				"{\n" +
				"    border-top-right-radius: 4px;\n" +
				"    border-top-left-radius: 4px;\n" +
				"}\n" +
				"\n" +
				"#optanon #optanon-popup-bottom {\n" +
				"    border-bottom-left-radius: 4px;\n" +
				"    border-bottom-right-radius: 4px;\n" +
				"}\n" +
				"\n" +
				".optanon-alert-box-wrapper .optanon-button-more .optanon-alert-box-button-middle a:before {\n" +
				"    content: '';\n" +
				"}\n" +
				".optanon-alert-box-wrapper .optanon-button-allow .optanon-alert-box-button-middle a:before {\n" +
				"    content: '';\n" +
				"}\n" +
				"\n" +
				".optanon-alert-box-wrapper .optanon-alert-box-body {\n" +
				"    margin-right: 390px;\n" +
				"}\n" +
				"\n" +
				"#optanon #optanon-popup-top .optanon-close, .optanon-alert-box-wrapper .optanon-alert-box-corner-close a {\n" +
				"    right: 10px;\n" +
				"}</style></head>\n" +
				"\n" +
				"<body class=\"plain error-page-wrapper background-color background-image\" data-gr-c-s-loaded=\"true\">\n" +
				"  <div class=\"content-container in\" style=\"top: 0px;\">\n" +
				"    <img class=\"logo\" src=\"data:image/png;base64,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\" height=\"50\">\n" +
				"\n" +
				"    <div class=\"head-line secondary-text-color\">\n" +
				"      402\n" +
				"    </div>\n" +
				"    <div class=\"subheader primary-text-color\">\n" +
				"      Payment Required <br>\n" +
				"    </div>\n" +
				"    <hr>\n" +
				"    <div class=\"clearfix\"></div>\n" +
				"    <div class=\"context primary-text-color\">\n" +
				"      <!-- doesn't use context_content because it's ALWAYS the same thing -->\n" +
				"      <div style=\"font-size: 26px;\"><span class=\"bitcoin\">ℏ</span> <strong>340 </strong> &rarr; <span class=\"address selectable\">address: 1016</span></div>\n" +
				"      <p>\n" +
				"        Transfer the required HBAR amount to the above mentioned address. Don't forget to always add a miners-fee!\n" +
				"      </p>\n" +
				"    </div>\n" +
				"    <div class=\"buttons-container\">\n" +
				"      <a class=\"border-button\" href=\"http://swapi.com:8080/api/people/1/\" target=\"_blank\">Go To Homepage</a>\n" +
				"      <a class=\"border-button\" href=\"mailto:hellopayper.ws\" target=\"_blank\">Report A Problem</a>\n" +
				"    </div>\n" +
				"  </div>\n" +
				"\n" +
				"\n" +
				"  <script>\n" +
				"    function ErrorPage(container, pageType, templateName) {\n" +
				"      this.$container = $(container);\n" +
				"      this.$contentContainer = this.$container.find(templateName == 'sign' ? '.sign-container' : '.content-container');\n" +
				"      this.pageType = pageType;\n" +
				"      this.templateName = templateName;\n" +
				"    }\n" +
				"\n" +
				"    ErrorPage.prototype.centerContent = function () {\n" +
				"      var containerHeight = this.$container.outerHeight()\n" +
				"        , contentContainerHeight = this.$contentContainer.outerHeight()\n" +
				"        , top = (containerHeight - contentContainerHeight) / 2\n" +
				"        , offset = this.templateName == 'sign' ? -100 : 0;\n" +
				"\n" +
				"      this.$contentContainer.css('top', top + offset);\n" +
				"    };\n" +
				"\n" +
				"    ErrorPage.prototype.initialize = function () {\n" +
				"      var self = this;\n" +
				"\n" +
				"      this.centerContent();\n" +
				"      this.$container.on('resize', function (e) {\n" +
				"        e.preventDefault();\n" +
				"        e.stopPropagation();\n" +
				"        self.centerContent();\n" +
				"      });\n" +
				"\n" +
				"      // fades in content on the plain template\n" +
				"      if (this.templateName == 'plain') {\n" +
				"        window.setTimeout(function () {\n" +
				"          self.$contentContainer.addClass('in');\n" +
				"        }, 500);\n" +
				"      }\n" +
				"\n" +
				"      // swings sign in on the sign template\n" +
				"      if (this.templateName == 'sign') {\n" +
				"        $('.sign-container').animate({ textIndent: 0 }, {\n" +
				"          step: function (now) {\n" +
				"            $(this).css({\n" +
				"              transform: 'rotate(' + now + 'deg)',\n" +
				"              'transform-origin': 'top center'\n" +
				"            });\n" +
				"          },\n" +
				"          duration: 1000,\n" +
				"          easing: 'easeOutBounce'\n" +
				"        });\n" +
				"      }\n" +
				"    };\n" +
				"\n" +
				"\n" +
				"    ErrorPage.prototype.createTimeRangeTag = function (start, end) {\n" +
				"      return (\n" +
				"        '<time utime=' + start + ' simple_format=\"MMM DD, YYYY HH:mm\">' + start + '</time> - <time utime=' + end + ' simple_format=\"MMM DD, YYYY HH:mm\">' + end + '</time>.'\n" +
				"      )\n" +
				"    };\n" +
				"\n" +
				"\n" +
				"    ErrorPage.prototype.handleStatusFetchSuccess = function (pageType, data) {\n" +
				"      if (pageType == '503') {\n" +
				"        $('#replace-with-fetched-data').html(data.status.description);\n" +
				"      } else {\n" +
				"        if (!!data.scheduled_maintenances.length) {\n" +
				"          var maint = data.scheduled_maintenances[0];\n" +
				"          $('#replace-with-fetched-data').html(this.createTimeRangeTag(maint.scheduled_for, maint.scheduled_until));\n" +
				"          $.fn.localizeTime();\n" +
				"        }\n" +
				"        else {\n" +
				"          $('#replace-with-fetched-data').html('<em>(there are no active scheduled maintenances)</em>');\n" +
				"        }\n" +
				"      }\n" +
				"    };\n" +
				"\n" +
				"\n" +
				"    ErrorPage.prototype.handleStatusFetchFail = function (pageType) {\n" +
				"      $('#replace-with-fetched-data').html('<em>(enter a valid Statuspage url)</em>');\n" +
				"    };\n" +
				"\n" +
				"\n" +
				"    ErrorPage.prototype.fetchStatus = function (pageUrl, pageType) {\n" +
				"      //console.log('in app.js fetch');\n" +
				"      if (!pageUrl || !pageType || pageType == '404') return;\n" +
				"\n" +
				"      var url = ''\n" +
				"        , self = this;\n" +
				"\n" +
				"      if (pageType == '503') {\n" +
				"        url = pageUrl + '/api/v2/status.json';\n" +
				"      }\n" +
				"      else {\n" +
				"        url = pageUrl + '/api/v2/scheduled-maintenances/active.json';\n" +
				"      }\n" +
				"\n" +
				"      $.ajax({\n" +
				"        type: \"GET\",\n" +
				"        url: url,\n" +
				"      }).success(function (data, status) {\n" +
				"        //console.log('success');\n" +
				"        self.handleStatusFetchSuccess(pageType, data);\n" +
				"      }).fail(function (xhr, msg) {\n" +
				"        //console.log('fail');\n" +
				"        self.handleStatusFetchFail(pageType);\n" +
				"      });\n" +
				"\n" +
				"    };\n" +
				"    var ep = new ErrorPage('body', \"404\", \"plain\");\n" +
				"    ep.initialize();\n" +
				"\n" +
				"    // hack to make sure content stays centered >_<\n" +
				"    $(window).on('resize', function () {\n" +
				"      $('body').trigger('resize')\n" +
				"    });\n" +
				"\n" +
				"  </script>\n" +
				"\n" +
				"\n" +
				"  <!-- OneTrust Cookies Consent Notice (statuspage.io, en-GB) start -->\n" +
				"  <script src=\"https://cdn.cookielaw.org/consent/0800f092-2124-46ec-b11d-b4f48b677302.js\" type=\"text/javascript\" charset=\"UTF-8\"></script>\n" +
				"  <script type=\"text/javascript\">\n" +
				"    function OptanonWrapper() { }\n" +
				"  </script>\n" +
				"  <!-- OneTrust Cookies Consent Notice (statuspage.io, en-GB) end -->\n" +
				"\n" +
				"\n" +
				"\n" +
				"<div id=\"optanon\" class=\"modern\"><div id=\"optanon-popup-bg\"></div><div id=\"optanon-popup-wrapper\" role=\"dialog\" aria-modal=\"true\" tabindex=\"-1\" lang=\"en-GB\"><div id=\"optanon-popup-top\"><a href=\"javascript:void(0);\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Preferences Close Button');\" aria-label=\"Close\" class=\"optanon-close-link optanon-close optanon-close-ui\" title=\"Close Preference Centre\"><div id=\"optanon-close\" style=\"background: url(https://cdn.cookielaw.org/skins/3.6.24/default_flat_bottom_two_button_black/v2/images/optanon-pop-up-close.png);width:34px;height:34px;\"></div></a></div><div id=\"optanon-popup-body\"><div id=\"optanon-popup-body-left\"><div id=\"optanon-popup-body-left-shading\"></div><div id=\"optanon-branding-top-logo\" style=\"background-image: url(https://cdn.cookielaw.org/logos/5878/5878:statuspage.io/Atlassian%20white%20logo.png) !important;\"></div><ul id=\"optanon-menu\" aria-label=\"Navigation Menu\"><li class=\"menu-item-on menu-item-about\" title=\"Cookies at Atlassian\"><p><a href=\"javascript:void(0);\">Cookies at Atlassian</a></p></li><li class=\"menu-item-necessary menu-item-on\" title=\"Strictly Necessary Cookies\"><p><a href=\"javascript:void(0);\">Strictly Necessary Cookies</a></p></li><li class=\"menu-item-on menu-item-performance\" title=\"Performance Cookies\"><p><a href=\"javascript:void(0);\">Performance Cookies</a></p></li><li class=\"menu-item-on menu-item-functional\" title=\"Functional Cookies\"><p><a href=\"javascript:void(0);\">Functional Cookies</a></p></li><li class=\"menu-item-on menu-item-advertising\" title=\"Targeting Cookies\"><p><a href=\"javascript:void(0);\">Targeting Cookies</a></p></li><li class=\"menu-item-moreinfo menu-item-off\" title=\"Cookie Policy\"><p><a target=\"_blank\" href=\"https://www.atlassian.com/legal/cookies\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Preferences Cookie Policy');\">Cookie Policy</a></p></li></ul></div><div id=\"optanon-popup-body-right\"><h2 aria-label=\"true\">Cookie Preference Centre</h2><div class=\"vendor-header-container\"><h3></h3><div id=\"optanon-popup-more-info-bar\"><div class=\"optanon-status\"><div class=\"optanon-status-editable\"><form><fieldset><p><input aria-checked=\"false\" value=\"check\" id=\"chkMain\" checked=\"\" class=\"legacy-group-status optanon-status-checkbox\" type=\"checkbox\"><label for=\"chkMain\">Active</label></p></fieldset></form></div><div class=\"optanon-status-always-active optanon-status-on\"><p>Always Active</p></div></div></div></div><div id=\"optanon-main-info-text\"></div></div><div class=\"optanon-bottom-spacer\"></div></div><div id=\"optanon-popup-bottom\"> <a href=\"https://onetrust.com/poweredbyonetrust\" target=\"_blank\"><div id=\"optanon-popup-bottom-logo\" style=\"background: url(https://cdn.cookielaw.org/skins/3.6.24/default_flat_bottom_two_button_black/v2/images/cookie-collective-top-bottom.png);width:155px;height:35px;\" title=\"powered by OneTrust\"></div></a><div class=\"optanon-button-wrapper optanon-save-settings-button optanon-close optanon-close-consent\"><div class=\"optanon-white-button-left\"></div><div class=\"optanon-white-button-middle\"><a href=\"javascript:void(0);\" aria-label=\"Save Settings\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Preferences Save Settings');\">Save Settings</a></div><div class=\"optanon-white-button-right\"></div></div><div class=\"optanon-button-wrapper optanon-allow-all-button optanon-allow-all\" style=\"display: none;\"><div class=\"optanon-white-button-left\"></div><div class=\"optanon-white-button-middle\"><a href=\"javascript:void(0);\" aria-label=\"Allow All\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Preferences Allow All');\">Allow All</a></div><div class=\"optanon-white-button-right\"></div></div></div></div></div><div class=\"optanon-alert-box-wrapper  hide-cookie-setting-button\" style=\"bottom: 0px;\"><div class=\"optanon-alert-box-bottom-top\"><div class=\"optanon-alert-box-corner-close\"><a class=\"optanon-alert-box-close\" aria-label=\"Close\" tabindex=\"4\" href=\"javascript:void(0);\" title=\"Close Banner\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Banner Close Button');\"></a></div></div><div class=\"optanon-alert-box-bg\"><div class=\"optanon-alert-box-logo\"> </div><div class=\"optanon-alert-box-body\"><p class=\"legacy-banner-content\">This site uses cookies to improve your browsing experience, perform analytics and research, and conduct advertising. To change your preferences, see our Cookies &amp; Tracking Notice. Otherwise, closing the banner or clicking Accept all Cookies indicates you agree to the use of cookies on your device.<a href=\"https://www.atlassian.com/legal/cookies#how-can-you-opt-out\" class=\"banner-policy-link\" tabindex=\"1\" title=\"Policy Link\">&nbsp; Cookies &amp; Tracking Notice</a></p></div><div class=\"optanon-clearfix\"></div><div class=\"optanon-alert-box-button-container\"><div class=\"optanon-alert-box-button optanon-button-close\"><div class=\"optanon-alert-box-button-middle\"><a class=\"optanon-alert-box-close\" aria-label=\"Close\" href=\"javascript:void(0);\">Close</a></div></div><div class=\"optanon-alert-box-button optanon-button-allow\"><div class=\"optanon-alert-box-button-middle\"><a class=\"optanon-allow-all\" tabindex=\"3\" title=\"Accept Cookies\" aria-label=\"Accept all Cookies\" href=\"javascript:void(0);\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Banner Accept Cookies');\">Accept all Cookies</a></div></div><div class=\"optanon-alert-box-button optanon-button-more\"><div class=\"optanon-alert-box-button-middle\"><a class=\"optanon-toggle-display\" tabindex=\"2\" title=\"Cookie Settings\" aria-label=\"Cookie Settings\" href=\"javascript:void(0);\" onclick=\"Optanon.TriggerGoogleAnalyticsEvent('OneTrust Cookie Consent', 'Banner Open Preferences');\">Cookie Settings</a></div></div></div><div class=\"optanon-clearfix optanon-alert-box-bottom-padding\"></div></div></div></body>";

		return new ResponseEntity(content, HttpStatus.PAYMENT_REQUIRED);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, RedirectToPaymentDetailsGatewayFilterFactory filterFactory) {
		return builder.routes()
				.route("r1", r -> r.predicate(this::paymentReceiptMissing)
						.filters(f -> f.redirect(302, "http://swapi.com:8080/payment-required"))
						.uri("http://swapi.co/"))

				.route("r2", r -> r.predicate(this::paymentReceiptValid)
						.filters(f -> {sleep(5000); return f.removeRequestHeader(RECEIPT_HEADER); })
						.uri("http://swapi.co/"))

				.build();
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean paymentReceiptValid(ServerWebExchange serverWebExchange) {
		return validReceipt(serverWebExchange);
	}

	private boolean paymentReceiptMissing(ServerWebExchange serverWebExchange) {
		return !validReceipt(serverWebExchange);
	}

	@Bean
	RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 2);
	}

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.httpBasic().and()
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers("/anything/**").authenticated()
				.anyExchange().permitAll()
				.and()
				.build();
	}

	@Bean
	public MapReactiveUserDetailsService reactiveUserDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
		return new MapReactiveUserDetailsService(user);
	}


	public static final String RECEIPT_HEADER = "X-Payment-Receipt";

	private boolean validReceipt(ServerWebExchange exchange) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		String receipt = headers.getFirst(RECEIPT_HEADER);
		return !StringUtils.isBlank(receipt);
	}

	public static void main(String[] args) {
		SpringApplication.run(PayperGatewayApplication.class, args);
	}
}