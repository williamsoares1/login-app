import { useEffect, useState } from 'react';
import { FaUserAstronaut } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';
import { Input_component } from '../../component/input/Input_component';
import { Password_component } from '../../component/password_input/Password_input';
import { useUser } from '../../context/auth/AuthContext';
import '../../css/pages/login-page.css';
import { login_api } from '../../service/login_api/login_api';

export const Login_page = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState();
  const [senha, setSenha] = useState();
  const {user, setUser, findUser} = useUser();

  const loginDTO = {
    email,
    senha
  }

  useEffect(() => {
    findUser();
  }, )

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await login_api.post("/auth/login", loginDTO);
      setUser(response.data);
      navigate("/home");
    } catch (error) {
      console.log(error)
    }
  }

  const handleTeste = async (e) => {
    e.preventDefault();

    try {
      const response = await login_api.get("/auth/teste");
      console.log(response.data);
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <main>
        <section>
          <FaUserAstronaut size={100} color='var(--laranja)' />
          <h1>LOGIN</h1>
        </section>

        <section>
          <form onSubmit={handleSubmit}>
            <Input_component max={40} placeholder={"ex: user@email.com"} type={"text"} value={email} setValue={setEmail} label={"email"}/>
            <Password_component max={12} placeholder={"password"} type={"password"} value={senha} setValue={setSenha} label={"senha"}/>
            <button type='submit'>SUBMIT</button>
            <button onClick={handleTeste}>TESTE</button>
          </form>
        </section>
      </main>
    </>
  )
}
