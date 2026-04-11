import { type RouteConfig, index, route } from "@react-router/dev/routes";

export default [
  index("routes/home.tsx"),
  route("/auth/sign-in", "routes/auth/sign-in.tsx"),
  route("/dashboard", "routes/dashboard.tsx"),
] satisfies RouteConfig;
