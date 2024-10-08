import Particles from "react-particles";
import { loadFull } from "tsparticles";
import { useCallback } from "react";
import { Engine, IOptions, RecursivePartial } from "tsparticles-engine";

const Backgroud = () => {
  const options: RecursivePartial<IOptions> = {
    particles: {
      number: {
        value: 300,
        density: {
          enable: true,
          area: 800
        }
      },
      color: {
        value: "#fff",
        animation: {
          enable: true,
          speed: 20,
          sync: false
        }
      },
      shape: {
        type: "circle"
      },
      opacity: {
        value: { min: 0.3, max: 1},
        animation: {
          enable: true,
          speed: 1,
          minimumValue: 0.3,
          sync: false
        }
      },
      size: {
        value: { min: 0.7, max: 1 }
      },
      links: {
        enable: false,
      },
      move: {
        enable: true,
        speed: 1,
        direction: "top",
        random: true,
        straight: true,
        outModes: "out"
      }
    }
  };

  const particlesInit = useCallback(async (engine: Engine) => {
    await loadFull(engine);
  }, []);

  return (
    <div className="Backgroud" style={{
        position: 'absolute',
        top: 0,
        left: 0,
        width: '100vw',
        height: '100vh',
        zIndex: -1
      }}>
      <Particles options={options} init={particlesInit} />
    </div>
  );
};

export default Backgroud