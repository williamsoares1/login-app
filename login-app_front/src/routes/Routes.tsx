import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login_page } from "../pages/login_page/login_page";
import { Home } from "../pages/home/Home";
import { AuthProvider } from "../context/auth/AuthContext";
import Backgroud from "../component/background/Background";

const AppRoutes = () => {
   return (
       <Router>
            <Backgroud/>
            <AuthProvider>
                <Routes>
                    <Route element={<Login_page />} path="/" />
                    <Route element={<Home />} path="/Home" />
                </Routes>
            </AuthProvider>
        </Router>
   );
};

export default AppRoutes;