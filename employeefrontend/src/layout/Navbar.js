import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import authService from '../authentication/auth';


export default function Navbar() {

    const [currentUser, setCurrentUser] = useState(undefined);
  
    useEffect(() => {
      const user = authService.getCurrentUser();
  
      if (user) {
        setCurrentUser(user);
      }
    }, []);
  
    const logOut = () => {
      authService.logout();
    };
  return (
    <div>
        <nav className="navbar navbar-expand-lg navbar-Dark bg-primary">
  <div className="container-fluid">
    <a className="navbar-brand" href="/login">
        Employee Application
     </a>

     <li className="nav-item">
            <Link to={'/home'} className="btn btn-dark mx-2">
              Home
            </Link>
          </li>

         
    
  </div>
 

        {currentUser ? (
          <div className="navbar-nav ms-auto">
            <li className="nav-item">
              <a href="/login" className="btn btn-danger mx-2" onClick={logOut}>
                Logout
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className= "btn btn-outline-dark"to={"/login"} >
                Login
              </Link>
            </li>

            <li className="nav-item">
              <Link className="btn btn-outline-dark mx-2" to={"/signup"} >
                Signup
              </Link>
            </li>
          </div>
          
        )}
      
</nav>
    </div>
  )
}
