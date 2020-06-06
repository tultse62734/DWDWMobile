using Microsoft.Owin.Security.OAuth;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Security.Claims;
using DWDW_WebAPI.Services;

namespace DWDW_WebAPI.Providers
{
    public class CustomAuthorization : OAuthAuthorizationServerProvider
    {
        public override async Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            context.Validated();
        }
        public override async Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            using (UserSecurity _repo = new UserSecurity())
            {
                var user = _repo.ValidateUser(context.UserName, context.Password);
                if (user == null)
                {
                    context.SetError("invalid_grant", "Provided username and password is incorrect");
                    return;
                }
                var identity = new ClaimsIdentity(context.Options.AuthenticationType);
                identity.AddClaim(new Claim(ClaimTypes.Role, user.roleId.ToString()));
                identity.AddClaim(new Claim("Role", user.roleId.ToString()));
                identity.AddClaim(new Claim("ID", user.userId.ToString()));
                identity.AddClaim(new Claim("DateOfBirth", user.dateOfBirth.ToString()));
                identity.AddClaim(new Claim("Gender", user.Gender.ToString()));
                identity.AddClaim(new Claim("Status", user.isActive.ToString()));
                identity.AddClaim(new Claim(ClaimTypes.Name, user.fullName));
                identity.AddClaim(new Claim("Phone", user.phone.ToString()));

                context.Validated(identity);
            }
        }
    }
}