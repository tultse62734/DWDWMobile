using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Web;
using DWDW_WebAPI.Contants;
using DWDW_WebAPI.Services;
using Microsoft.Owin.Security.OAuth;

namespace DWDW_WebAPI.Providers
{
    public class AccountAuthorizationProvider : OAuthAuthorizationServerProvider
    {
        //Dùng để validate app hệ thống. Dùng để hiện thực tính năng Authentication vào app.
        public override async Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            context.Validated();
        }
        //Validate usernamevaf password. Cần phải có ValidateClient để ứng dụng có thể sử dụng.
        public override async Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            using (UserSecurityService _repo = new UserSecurityService())
            {
                var user = _repo.ValidateUser(context.UserName, context.Password);
                if (user == null)
                {
                    context.SetError(ErrorMessage.LOGIN_FAIL);
                    return;
                }
                //Mã hóa những dữ liệu cần có trong Bearer Token. Dùng để truy xuất xâu xa hơn về sau.
                var identity = new ClaimsIdentity(context.Options.AuthenticationType);
                identity.AddClaim(new Claim(ClaimTypes.Role, user.roleId.ToString()));
                identity.AddClaim(new Claim("ID", user.userId.ToString()));
                context.Validated(identity);
            }
        }
    }
}