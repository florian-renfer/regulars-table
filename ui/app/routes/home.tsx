import type { Route } from "./+types/home";
import { Welcome } from "../welcome/welcome";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "Regulars Table" },
    {
      name: "description",
      content:
        "An application allowing you to meet your friends more frequently.",
    },
  ];
}

export default function Home() {
  return <Welcome />;
}
