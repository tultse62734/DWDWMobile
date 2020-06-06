using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Owin;
using Owin;
using Microsoft.Owin.Security.OAuth;
using System.Web.Http;
using DWDW_WebAPI.Providers;

[assembly: OwinStartup(typeof(DWDW_WebAPI.Startup))]

namespace DWDW_WebAPI
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            //Cho phép truy cập từ nhiều trình duyệt
            app.UseCors(Microsoft.Owin.Cors.CorsOptions.AllowAll);

            OAuthAuthorizationServerOptions options = new OAuthAuthorizationServerOptions
            {
                AllowInsecureHttp = true,

                //Đường dẫn khởi tạo token
                TokenEndpointPath = new PathString("/token"),

                //Xét thời gian token hết hạn - 3 tiếng
                AccessTokenExpireTimeSpan = TimeSpan.FromHours(3),

                //Validate người dùng
                Provider = new CustomAuthorization()
            };

            //Tạo token
            app.UseOAuthAuthorizationServer(options);
            app.UseOAuthBearerAuthentication(new OAuthBearerAuthenticationOptions());

            HttpConfiguration config = new HttpConfiguration();
            WebApiConfig.Register(config);
            //ConfigureAuth(app);
        }
    }
}
