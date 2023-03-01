import React, { useState } from "react";
import authService from "../authentication/auth";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await authService.signup(userName, password).then(
        (response) => {
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
            <h2 className='text-center m-4'>Signup User</h2>
      <form onSubmit={handleSignup}>
      <div className='mb-3'>
                    <label htmlFor='userName' className='form-label'>
                        User Name
                    </label>
        <input
          type="text"
          className='form-control'
          placeholder="enter username"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        />
        </div>
        <div className='mb-3'>
                    <label htmlFor='password' className='form-label'>
                        password
                    </label>
        <input
          type="password"
          className='form-control'
          placeholder="enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        </div>
        <button type="submit" className='btn btn-outline-primary'>Sign up</button>
      </form>
    </div>
    </div>
    </div>
  );
};

export default Signup;