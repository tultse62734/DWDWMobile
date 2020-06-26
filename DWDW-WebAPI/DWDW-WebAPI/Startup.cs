using System;
using System.Threading.Tasks;
using Microsoft.Owin;
using Owin;
using Microsoft.Owin.Security.OAuth;
using System.Web.Http;
using Microsoft.Owin.Security.Jwt;
using Microsoft.Owin.Security;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using DWDW_WebAPI.Contants;

[assembly: OwinStartup(typeof(DWDW_WebAPI.Startup))]

namespace DWDW_WebAPI
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            app.UseJwtBearerAuthentication(
                new JwtBearerAuthenticationOptions
                {
                    AuthenticationMode = AuthenticationMode.Active,
                    TokenValidationParameters = new TokenValidationParameters()
                    {
                        ValidateIssuer = true,
                        ValidateAudience = true,
                        ValidateIssuerSigningKey = true,
                        ValidIssuer = Constant.URL, //some string, normally web url,  
                        ValidAudience = Constant.URL,
                        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Constant.SECRET_KEY))
                    }
                });
        }

        //public void Configuration(IAppBuilder app)
        //{
        //    //Cho phép truy cập từ nhiều trình duyệt hoặc app
        //    app.UseCors(Microsoft.Owin.Cors.CorsOptions.AllowAll);

        //    OAuthAuthorizationServerOptions options = new OAuthAuthorizationServerOptions
        //    {
        //        AllowInsecureHttp = true,

        //        //Đường dẫn khởi tạo token
        //        TokenEndpointPath = new PathString("/token"),

        //        //Xét thời gian token hết hạn - 3 tiếng
        //        AccessTokenExpireTimeSpan = TimeSpan.FromHours(24),

        //        //Validate người dùng
        //        Provider = new AccountAuthorizationProvider()
        //    };

        //    //Tạo token
        //    app.UseOAuthAuthorizationServer(options);
        //    app.UseOAuthBearerAuthentication(new OAuthBearerAuthenticationOptions());

        //    HttpConfiguration config = new HttpConfiguration();
        //    WebApiConfig.Register(config);
        //    //ConfigureAuth(app);
        //}
    }
}
