import axios from "axios";



const accessToken = localStorage.getItem('Authorization');
axios.defaults.headers.common['Authorization']= `${accessToken}`

const signup = (userName, password) => {
    return axios
      .post("http://localhost:8080/User/signup", {
        userName,
        password,
      })
      .then((res) => {
        if (res.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(res.data));
        }
  
        return res.data;
      });
  };



const login = (user) => {
  return axios
    .post("http://localhost:8080/User/login", user)
    .then(response => {
      localStorage.setItem("user", JSON.stringify(response.data));
      const jwtToken = response.data;
      localStorage.setItem("Authorization", jwtToken);

        return response.data;
      
    });
};

 const logout = () => {
   localStorage.removeItem("user");
   localStorage.removeItem("Authorization");
 };

 const getCurrentUser = () => {
   return JSON.parse(localStorage.getItem("user"));
};

const authService = {
  signup,
  login,
  logout,
  getCurrentUser
};

export default authService;