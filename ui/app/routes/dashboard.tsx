import { redirect } from "react-router";

export async function clientLoader() {
  const res = await fetch(`/api/users/me`, {
    credentials: "include",
  });

  if (res.status === 401) {
    throw redirect("/auth/sign-in");
  }

  if (!res.ok) {
    throw new Error("Failed to load the current user");
  }

  return await res.text();
}

// HydrateFallback is rendered while the client loader is running
export function HydrateFallback() {
  return <div>Loading...</div>;
}

export default function Dashboard({ loaderData }: { loaderData: string }) {
  const givenName = loaderData;

  return (
    <div className="md:flex md:items-center md:justify-between">
      <div className="min-w-0 flex-1">
        <h2 className="text-2xl/7 font-bold text-gray-900 sm:truncate sm:text-3xl sm:tracking-tight dark:text-white">
          Hallo, {givenName}
        </h2>
      </div>
      <div className="mt-4 flex md:mt-0 md:ml-4">
        <form action="/api/logout" method="post">
          <button
            type="submit"
            className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-xs inset-ring inset-ring-gray-300 hover:bg-gray-50 dark:bg-white/10 dark:text-white dark:shadow-none dark:inset-ring-white/5 dark:hover:bg-white/20"
          >
            Sign Out
          </button>
        </form>
        <button
          type="button"
          className="ml-3 inline-flex items-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-xs hover:bg-indigo-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 dark:bg-indigo-500 dark:shadow-none dark:hover:bg-indigo-400 dark:focus-visible:outline-indigo-400"
        >
          Publish
        </button>
      </div>
    </div>
  );
}
