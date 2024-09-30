import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Home } from "../pages/home/Home";
import { AuthProvider } from "../context/auth/AuthContext";
import Backgroud from "../component/background/Background";
import { LoginPage } from "../pages/loginPage/loginPage";

const AppRoutes = () => {
   return (
       <Router>
            <Backgroud/>
            <AuthProvider>
                <Routes>
                    <Route element={<LoginPage />} path="/" />
                    <Route element={<Home />} path="/Home" />
                </Routes>
            </AuthProvider>
        </Router>
   );
};

export default AppRoutes;