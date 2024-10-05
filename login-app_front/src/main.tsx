import ReactDOM from 'react-dom/client';
import './index.css';
import AppRoutes from './routes/Routes.tsx';
import { setupInterceptor } from './service/login_api/intercptor.tsx';

setupInterceptor();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <AppRoutes/>
)
