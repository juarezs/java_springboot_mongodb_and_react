import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '');

  console.debug(`api url: ${env.API_BASE_URL}`);
  
  return {
    plugins: [react()],
    resolve: {
        alias: {
          "@": path.resolve(__dirname, "./src"),
        },
      },
    server: {
      port: 3000,
      proxy: {
          '/api': {
            target: env.API_BASE_URL,
            changeOrigin: true,
            secure: false,
          }
        }
    },
    test: {
      globals: true,
      environment: 'jsdom',
    },
  }
});
