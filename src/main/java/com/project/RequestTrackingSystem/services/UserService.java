package com.project.RequestTrackingSystem.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
	public User validate(User user) {
		User argUser;	
		String msg;
		boolean isEmail = validateEmail(user.getUserEmail());
		
		if(isEmail) {
			argUser = userRepo.findByUserEmail(user.getUserEmail());
			System.out.println(argUser);
			System.out.println(userRepo.findByUserEmail(user.getUserEmail()));
			
			if(argUser == null) {
				msg = "Email doesn't Exist";
			} else if(user.getUserPassword().compareTo(argUser.getUserPassword()) != 0) {
				msg = "Invalid Email or Password";
			} else {
				msg = "Login Successful";
			}
			
		}
		
		else {
			user.setUserName(user.getUserEmail());
			user.setUserEmail(null);
			argUser = userRepo.findByUserName(user.getUserName());
			System.out.println(argUser);
			
			System.out.println(userRepo.findByUserName(user.getUserName()));
			if(argUser == null) {
				msg = "UserName doesn't Exist";
			} else if(user.getUserPassword().compareTo(argUser.getUserPassword()) != 0) {
				msg = "Invalid UserName or Password";
			} else {
				msg = "Login Successful";
			}
		}
		if(argUser == null) {
			argUser = new User();
		}
		argUser.setMsg(msg);
		return argUser;
		
		
		
	}
	
	
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	
	public boolean passwordValidator(String password) {
		if(password.length() >= 8) {
			
			boolean upper = false;
			boolean lower = false;
			boolean numeric = false;
			boolean special = false;
			
			for(int i = 0; i < password.length(); i++) {
				int asciiVal = (int)password.charAt(i);
				if( asciiVal >= 65 && asciiVal <= 90 ) {
					upper = true;
				}
				else if( asciiVal >= 97 && asciiVal <= 122 ) {
					lower = true;
				}
				else if( asciiVal >= 48 && asciiVal <= 57 ) {
					numeric = true;
				}
				else {
					special = true;
				}	
			}
			
			
			if(special && numeric && lower && upper) {
				return true;
			} else {
				return false;
			}
		
		} else {
			return false;
		}
	}
	
	
	
	public ChangePassword verifyPassword(ChangePassword pass) {
		ChangePassword argPass = new ChangePassword();
		String msg;
		argPass.setUserId(pass.getUserId());
		User user = userRepo.findById(pass.getUserId()).get();
		if(user.getUserPassword().compareTo(pass.getOldPassword()) == 0) {
			if(pass.getNewPassword().compareTo(pass.getConfirmPassword()) == 0) {
				if(pass.getNewPassword().compareTo(pass.getOldPassword()) != 0) {
					if(this.passwordValidator(pass.getNewPassword())) {
						user.setUserPassword(pass.getNewPassword());
						this.save(user);
						msg = "Password Changed";
					} else {
						msg = "Weak Password!! <Password must contain upper case, lower case, numeric and special characters and should be"
								+ "atleast 8 characters long> !!!";
					}
				} else {
					msg = "New Password cannot be same as Old Password!!";
				}
			} else {
				msg = "New Password and Confirm Password didnot match!!";
			}
		} else {
			msg = "Old Password did not match with current password!!";
		}
		argPass.setMsg(msg);
		return argPass;
	}
	
	public ChangePassword changeAnyPassword(ChangePassword pass) {
		ChangePassword argPass = new ChangePassword();
		String msg;
		boolean isEmail = validateEmail(pass.getEmailName());
		User user;
		
		if(isEmail) {
			user = userRepo.findByUserEmail(pass.getEmailName());		
		} else {
			user = userRepo.findByUserName(pass.getEmailName());	
		}
		
		if(user != null) {
			if(pass.getNewPassword().compareTo(pass.getConfirmPassword()) == 0) {
				if(pass.getNewPassword().compareTo(user.getUserPassword()) != 0) {
					String passwordMsg = this.isPasswordValid(pass.getNewPassword());
					if(passwordMsg.compareTo("Strong") == 0) {
						user.setUserPassword(pass.getNewPassword());
						userRepo.save(user);
						msg = "Password Changed Successfully";
					} else {
						msg = passwordMsg;
					}
				} else {
					msg = "New Password cannot be same as old password";
				}
			} else {
				msg = "New Password and Confirm Password did not match!!";
			}
		} else {
			if(isEmail) {
				msg = "Email does not exist";
			}else {
				msg="Username does not exist";
			}
			
		}
		
		argPass.setMsg(msg);
		return argPass;
	}
	
	public String isPasswordValid(String samplepassword)
    {
        int integercheck=0;
        int capitalcheck=0;
        int smallcheck=0;
        boolean valid=true;
        String msg = "Strong";

        if (!((samplepassword.length() >= 8) && (samplepassword.length() <= 20)) && valid) {
            msg = "Password Length must be of 8 characer long";
            valid=false;

        }

        if (samplepassword.contains(" ") && valid) {
            msg = "Password Must Not Contain a Space";
            valid=false;
        }

        
        if (true) {
            for (int i = 0; i <= 9; i++) {

                String str1 = Integer.toString(i);

                if (samplepassword.contains(str1)) {
                    integercheck = 1;
                }
            }
            if ((integercheck == 0) && valid) {
                msg = "Password Must Contain a Numeric Value";
                valid=false;            }
        }

        if (!(samplepassword.contains("@") || samplepassword.contains("#")
            || samplepassword.contains("!") || samplepassword.contains("$") 
            || samplepassword.contains("*") || samplepassword.contains("&")
            || samplepassword.contains(".")) && valid) {
            msg = "Password Must contain a special Character";
            valid=false;        }

        if (true) {
            for (int i = 65; i <= 90; i++) {

                char c = (char)i;

                String str1 = Character.toString(c);
                if (samplepassword.contains(str1)) {
                    capitalcheck = 1;
                }
            }
            if ((capitalcheck == 0) && valid) {
                msg = "Password Must Contain Capital Letter";
                valid=false;
            }
        }

        if (true) {
            for (int i = 97; i <= 122; i++) {

                char c = (char)i;
                String str1 = Character.toString(c);

                if (samplepassword.contains(str1)) {
                    smallcheck = 1;
                }
            }
            if ((smallcheck == 0) && valid){
                msg = "Password Must Contain a Small Character";
                valid=false;
            }
        }
        
        
        if (valid==true){
//        	If password satisfies all the conditions... <Good Password>
        	msg = "Strong";
            return msg;
        }else{
        	
//        	If password did not satisfy any of the condition
            return msg;
        }
    }

}
