
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../authentication/auth";


export default function Login()  {
  
  const navigate = useNavigate();

  const [user, setUser] = useState({
    userName: "",
    password: ""
  });

  const{userName, password}=user;
  const onInputChange=(e)=>{
    const value = e.target.value;
    setUser({...user, [e.target.name]:value});
};

 

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await authService.login(user).then(
         
        () => {
          
          
          navigate("/home");
          window.location.reload();
         
        },
        (error) => {
          console.log(error);
        }
      );
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className='container'>
    <div className='row'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
            <h2 className='text-center m-4'>Login User</h2>
      <form onSubmit={handleLogin}>
      <div className='mb-3'>
                    <label htmlFor='userName' className='form-label'>
                        User Name
                    </label>
                    <input
                    type={"text"}
                    className='form-control'
                    placeholder='Enter User Name'
                    name='userName'
                    value={userName}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='password' className='form-label'>
                        password
                    </label>
                    <input
                    type={"password"}
                    className='form-control'
                    placeholder='Enter password'
                    name='password'
                    value={password}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
        <button type="submit" className='btn btn-outline-primary'>Log in</button>
      </form>
    </div>
    </div>
    </div>
  );
};

